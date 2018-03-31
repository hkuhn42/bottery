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
/**
 * 
 */
package rocks.bottery.connector;

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
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ReplyKeyboardRemove;
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
import rocks.bottery.bot.IParticipant;
import rocks.bottery.connector.console.ConnectorBase;
import rocks.bottery.messaging.IReceiver;

/**
 * Connector for telegram
 * 
 * https://github.com/pengrad/java-telegram-bot-api#creating-your-bot
 * 
 * @author Harald Kuhn
 */
public class TelegramConnector extends ConnectorBase {

	private Logger		logger = LoggerFactory.getLogger(TelegramConnector.class);

	private TelegramBot	telegramBot;
	private String		name;

	public TelegramConnector() {
		this("telegram");
	}

	public TelegramConnector(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IConnector#listen(org.sylvani.bot.IBot)
	 */
	@Override
	public void register(IReceiver receiver) {
		String key = config.getSetting(name + ".key");
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
						receiver.receive(createActivity(update), TelegramConnector.this);
					}
				}

				telegramBot.setUpdatesListener(new UpdatesListener() {
					@Override
					public int process(List<Update> updates) {

						// process updates
						if (updates != null) {
							for (Update update : updates) {
								logger.debug("received " + update);
								receiver.receive(createActivity(update), TelegramConnector.this);
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

		if (data.getChoices() != null && data.getChoices().size() > 0) {
			KeyboardButton[] buttons = new KeyboardButton[data.getChoices().size()];
			for (int i = 0; i < data.getChoices().size(); i++) {
				buttons[i] = new KeyboardButton(data.getChoices().get(i).getLabelString());
			}
			ReplyKeyboardMarkup replyMarkup = new ReplyKeyboardMarkup(buttons);
			request.replyMarkup(replyMarkup);
			logger.debug("added keyboard with " + data.getChoices().size() + " choices");
		}
		else {
			request.replyMarkup(new ReplyKeyboardRemove());
		}

		// async
		telegramBot.execute(request, new Callback<SendMessage, SendResponse>() {
			@Override
			public void onResponse(SendMessage request, SendResponse response) {
				logger.debug("onResponse " + response.description());
			}

			@Override
			public void onFailure(SendMessage request, IOException e) {
				logger.debug("onFailure", e);
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
								logger.debug("onResponse " + response.description());
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

	public static void main(String[] args) {
		TelegramConnector c = new TelegramConnector("telegram");
		c.register(null);
		c.send(null);
	}

	@Override
	public IParticipant getConnectorAccount() {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public void shutdown() {
		telegramBot.removeGetUpdatesListener();
	}

	@Override
	public String getChannel() {
		return "telegram";
	}
}
