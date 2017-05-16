/**
 *    Copyright (C) 2016-2017 Harald Kuhn
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
/**
 * 
 */
package rocks.bottery.bot.connector.telegram;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.GetFileResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

import rocks.bottery.bot.AttachmentType;
import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IAttachment;
import rocks.bottery.bot.IBot;
import rocks.bottery.bot.IConnector;
import rocks.bottery.bot.IParticipant;
import rocks.bottery.bot.connector.GenericActivity;
import rocks.bottery.bot.connector.GenericAttachment;

/**
 * A connector for telegram implemented with 
 *
 *         https://github.com/pengrad/java-telegram-bot-api#creating-your-bot
 *         
 *  The api key must be configured with the bot setting property <connector-name>.key
 *  e.g.
 *  telegram.key = xxxxx if telegram is the connector name (set with constructor)          
 *
 * @author Harald Kuhn
 */
public class TelegramConnector implements IConnector {

	private Logger		logger = LoggerFactory.getLogger(TelegramConnector.class);

	private TelegramBot	telegramBot;
	private String		name;

	/**
	 * Create a new connector with the given name
	 * 
	 * @param name
	 */
	public TelegramConnector(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IConnector#listen(org.sylvani.bot.IBot)
	 */
	@Override
	public void listen(IBot bot) {
		String key = bot.getBotConfig().getSetting(name + ".key");
		telegramBot = TelegramBotAdapter.build(key);

		GetUpdates getUpdates = new GetUpdates().limit(100).offset(0).timeout(0);

		// async
		telegramBot.execute(getUpdates, new Callback<GetUpdates, GetUpdatesResponse>() {
			@Override
			public void onResponse(GetUpdates request, GetUpdatesResponse response) {
				logger.debug("response " + response.description());
				if (response.errorCode() > 0) {
					return;
				}
				List<Update> updates = response.updates();
				if (updates != null) {
					for (Update update : updates) {
						logger.debug("received " + update);
						bot.receive(createActivity(update));
					}
				}

				telegramBot.setUpdatesListener(new UpdatesListener() {
					@Override
					public int process(List<Update> updates) {

						// process updates
						if (updates != null) {
							for (Update update : updates) {
								logger.debug("received " + update);
								bot.receive(createActivity(update));
							}
						}
						return UpdatesListener.CONFIRMED_UPDATES_ALL;
					}
				});
			}

			protected TelegramActivity createActivity(Update update) {
				TelegramActivity telegramActivity = new TelegramActivity(update);
				if (update.message().photo() != null) {

					PhotoSize photo = null;
					for (PhotoSize photoSize : update.message().photo()) {
						if (photo == null || photo.fileSize() < photoSize.fileSize()) {
							photo = photoSize;
						}
					}
					try {
						GenericAttachment attachment = newAttachment(photo.fileId());
						attachment.setType(AttachmentType.PHOTO);
						telegramActivity.getAttachments().add(attachment);
					}
					catch (MalformedURLException e) {
						e.printStackTrace();
					}
				}

				return telegramActivity;
			}

			@Override
			public void onFailure(GetUpdates request, IOException e) {
				e.printStackTrace();
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IConnector#send(org.sylvani.bot.IActivity)
	 */
	@Override
	public void send(IActivity data) {

		SendMessage request = new SendMessage(Long.valueOf(data.getConversation().getId()), data.getText()).parseMode(ParseMode.HTML)
		        .disableWebPagePreview(true).disableNotification(true).replyToMessageId(1).replyMarkup(new ForceReply());

		// async
		telegramBot.execute(request, new Callback<SendMessage, SendResponse>() {
			@Override
			public void onResponse(SendMessage request, SendResponse response) {
				System.out.println(response.description());
			}

			@Override
			public void onFailure(SendMessage request, IOException e) {
				e.printStackTrace();
			}
		});

		if (data != null && data.getAttachments() != null && !data.getAttachments().isEmpty()) {
			for (IAttachment attachment : data.getAttachments()) {
				switch (attachment.getType()) {
					case PHOTO:
						SendPhoto photoRequest = new SendPhoto(Long.valueOf(data.getConversation().getId()), new java.io.File("D:/Dev/temp/DSCN2623.JPG"));
						// fixme: move to
						telegramBot.execute(photoRequest, new Callback<SendPhoto, SendResponse>() {
							@Override
							public void onResponse(SendPhoto request, SendResponse response) {
								System.out.println(response.description());
							}

							@Override
							public void onFailure(SendPhoto request, IOException e) {
								e.printStackTrace();
							}
						});

						break;
					default:
						break;
				}
			}
		}
	}

	@Override
	public IParticipant getConnectorAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IActivity newMessageTo(IParticipant recipientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IActivity newReplyTo(IActivity toThisActivity) {
		GenericActivity activity = new GenericActivity();
		activity.setRecipient(toThisActivity.getFrom());
		activity.setConversation(toThisActivity.getConversation());
		return activity;
	}

	private GenericAttachment newAttachment(String fileId) throws MalformedURLException {
		GenericAttachment attachment = new GenericAttachment();
		GetFile request = new GetFile(fileId);
		GetFileResponse getFileResponse = telegramBot.execute(request);

		File file = getFileResponse.file(); // com.pengrad.telegrambot.model.File
		attachment.setId(file.fileId());
		attachment.setName(file.filePath());
		attachment.setSize(file.fileSize());
		String fullPath = telegramBot.getFullFilePath(file); // com.pengrad.telegrambot.model.File
		attachment.setUrl(new URL(fullPath));
		return attachment;
	}
}
