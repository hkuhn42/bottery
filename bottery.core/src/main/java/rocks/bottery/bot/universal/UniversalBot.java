/**
 * Copyright (C) 2016-2017 Harald Kuhn
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

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.rincl.Rincled;
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
import rocks.bottery.bot.recognizers.IRecognizer;
import rocks.bottery.connector.IConnector;

/**
 * General purpose bot implementation
 * 
 * @author Harald Kuhn
 */
public class UniversalBot extends ContextBase implements IBot, Rincled {

	private Logger					  logger = LoggerFactory.getLogger(UniversalBot.class);

	private IDialog					  welcomeDialog;

	private Map<IRecognizer, IDialog> globalCommands;

	private Map<IRecognizer, IDialog> dialogs;

	private IBotConfig				  botConfig;

	private Stack<IHandler>			  inInterceptorChain;
	private Stack<IHandler>			  outInterceptorChain;

	public UniversalBot() {
		this(new UniversalBotConfig());
	}

	public UniversalBot(IBotConfig botConfig) {
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
			session = createSession(connector, convId);
		}
		inInterceptorChain.peek().handle(session, activity);
		// handle(session, activity);
	}

	protected ISession createSession(IConnector connector, String convId) {
		logger.debug("new session");
		ISession session = new UniversalSession(convId, this, connector);
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
		for (IRecognizer recognizer : globalCommands.keySet()) {
			if (recognizer.recognize(session, activity) > 0) {
				IDialog dialog = globalCommands.get(recognizer);
				logger.debug("delegating message to global " + dialog.getClass().getName());
				return dialog;
			}
		}
		if (session.getActiveDialog() != null) {
			logger.debug("delegating message to active " + session.getActiveDialog().getClass().getName());
			return session.getActiveDialog();
		}

		for (IRecognizer recognizer : dialogs.keySet()) {
			if (recognizer.recognize(session, activity) > 0) {
				IDialog dialog = dialogs.get(recognizer);
				logger.debug("delegating message to " + dialog.getClass().getSimpleName());
				return dialog;
			}
		}
		logger.debug("coult not find match, delegating to welcome");
		return welcomeDialog;
	}

	public void addDialog(IRecognizer recognizer, IDialog dialog) {
		dialogs.put(recognizer, dialog);
	}

	public void addGlobalCommand(IRecognizer recognizer, IDialog dialog) {
		globalCommands.put(recognizer, dialog);
	}

	public void setWelcomeDialog(IDialog welcomeDialog) {
		this.welcomeDialog = welcomeDialog;
	}

	@Override
	public IBotConfig getBotConfig() {
		return botConfig;
	}

	@Override
	public void invalidate(ISession session) {
		botConfig.getSessionStore().remove(session);
	}

}
