/**
 * Copyright (C) 2016-2018 Harald Kuhn
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package rocks.bottery.bot.universal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rocks.bottery.bot.ActivityType;
import rocks.bottery.bot.ContextBase;
import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IBot;
import rocks.bottery.bot.IBotConfig;
import rocks.bottery.bot.IHandler;
import rocks.bottery.bot.ISession;
import rocks.bottery.bot.dialogs.IDialog;
import rocks.bottery.bot.interceptors.DuplicateMessageFilter;
import rocks.bottery.bot.interceptors.IInterceptor;
import rocks.bottery.bot.recognizers.IIntent;
import rocks.bottery.bot.recognizers.IRecognizer;
import rocks.bottery.connector.IConnector;
import rocks.bottery.messaging.IMessagingContext;
import rocks.bottery.messaging.MessagingContext;

/**
 * General purpose bot implementation. This should be sufficient for most needs.The actual behavior of bots is defined
 * by {@link IDialog} implementations and {@link IRecognizer}.
 * 
 * 
 * @author Harald Kuhn
 */
public class UniversalBot extends ContextBase implements IBot {

	private Logger				 logger	= LoggerFactory.getLogger(UniversalBot.class);

	private IDialog				 welcomeDialog;

	private Map<String, IDialog> globalCommands;

	private Map<String, IDialog> dialogs;

	private IBotConfig			 botConfig;
	private IMessagingContext	 messagingContext;

	private Stack<IHandler>		 inInterceptorChain;
	private Stack<IHandler>		 outInterceptorChain;

	public UniversalBot() {
		init(new UniversalBotConfig(this.getClass().getName()));
	}

	public UniversalBot(IBotConfig botConfig) {
		init(botConfig);
	}

	protected void init(IBotConfig botConfig) {
		messagingContext = new MessagingContext();
		this.botConfig = botConfig;
		this.dialogs = new HashMap<>();
		this.globalCommands = new HashMap<>();

		inInterceptorChain = new Stack<>();
		inInterceptorChain.push(this); // the bot is the final in handler
		if (botConfig.getArchive() != null) {
			botConfig.getArchive().chain(inInterceptorChain.peek());
			inInterceptorChain.push(botConfig.getArchive());
		}

		if (true) {// fixme: add to config
			inInterceptorChain.push(new DuplicateMessageFilter(botConfig.getArchive()));
		}

		if (!botConfig.getInInterceptors().isEmpty()) {
			for (IInterceptor interceptor : botConfig.getInInterceptors()) {
				interceptor.chain(inInterceptorChain.peek());
				inInterceptorChain.push(interceptor);
			}
		}

		outInterceptorChain = new Stack<>();
		outInterceptorChain.push(this); // the bot is the final out handler, too
		if (botConfig.getArchive() != null) {
			outInterceptorChain.push(botConfig.getArchive());
		}

		if (!botConfig.getOutInterceptors().isEmpty()) {
			for (IInterceptor interceptor : botConfig.getOutInterceptors()) {
				interceptor.chain(outInterceptorChain.peek());
				outInterceptorChain.push(interceptor);
			}
		}

	}

	@Override
	public void receive(IActivity activity, IConnector connector) {
		String convId = activity.getConversation().getId();
		ISession session = botConfig.getSessionStore().find(convId);
		logger.debug("receive activity of  type " + activity.getType() + " " + activity.getText() + " " + activity.getConversation().getId());
		if (session == null) {
			Locale locale = activity.getLocale();
			if (locale == null) {
				locale = botConfig.getDefaultLocale();
			}
			if (locale == null) {
				locale = Locale.getDefault();
			}
			session = createSession(connector, convId, locale);
		}
		inInterceptorChain.peek().handle(session, activity);
		// handle(session, activity);
	}

	protected ISession createSession(IConnector connector, String convId, Locale locale) {
		logger.debug("new session");
		ISession session = new UniversalSession(convId, this, connector, locale);
		botConfig.getSessionStore().add(convId, session);
		return session;
	}

	@Override
	public void handle(ISession session, IActivity activity) {
		if (ActivityType.MESSAGE == activity.getType() || ActivityType.NEW_CONTACT == activity.getType()) {
			IDialog dialog = findDialog(session, activity);
			((UniversalSession) session).setActiveDialog(dialog);

			dialog.handle(session, activity);
		}
	}

	private IDialog findDialog(ISession session, IActivity activity) {
		List<IIntent> possibleIntents = new ArrayList<>();
		for (IRecognizer recognizer : getBotConfig().getRecognizers()) {
			IIntent intent = recognizer.recognize(session, activity);
			if (intent != null) {
				possibleIntents.add(intent);
				// TODO: check wether recognizer strategy should be configurable
				break;
			}
		}
		// fixme: sort on confidence
		if (possibleIntents.size() > 0) {
			activity.setIntent(possibleIntents.get(0));
		}

		// check for global commands
		for (IIntent intent : possibleIntents) {
			if (intent.getConfidence() == 1) {
				IDialog dialog = globalCommands.get(intent.getIntentName());
				if (dialog != null) {
					activity.setIntent(intent);
					logger.debug("delegating message to global " + dialog.getClass().getName());
					return dialog;
				}

			}
		}
		// check if active dialog
		if (session.getActiveDialog() != null) {
			logger.debug("delegating message to active " + session.getActiveDialog().getClass().getName());
			return session.getActiveDialog();
		}

		// check for regular dialogs
		for (IIntent intent : possibleIntents) {
			if (intent.getConfidence() == 1) {
				IDialog dialog = dialogs.get(intent.getIntentName());
				if (dialog != null) {
					activity.setIntent(intent);
					logger.debug("delegating message to global " + dialog.getClass().getName());
					return dialog;
				}
			}
		}

		// return welcome
		logger.debug("coult not find match, delegating to welcome");
		return welcomeDialog;
	}

	public void addDialog(String intent, IDialog dialog) {
		dialogs.put(intent, dialog);
	}

	public void addGlobalCommand(String intent, IDialog dialog) {
		globalCommands.put(intent, dialog);
	}

	public void setWelcomeDialog(IDialog welcomeDialog) {
		this.welcomeDialog = welcomeDialog;
	}

	/**
	 * shortcut for recognizer init and add recognizer to config
	 * 
	 * @param recognizer
	 * @throws IOException
	 */
	public void addRecognizer(IRecognizer recognizer) throws IOException {
		recognizer.init(getBotConfig());
		getBotConfig().getRecognizers().add(recognizer);
	}

	@Override
	public IBotConfig getBotConfig() {
		return botConfig;
	}

	@Override
	public void invalidate(ISession session) {
		botConfig.getSessionStore().remove(session);
	}

	@Override
	public IMessagingContext getMessagingContext() {
		return messagingContext;
	}

}
