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
package rocks.bottery.connector.discord;

import java.net.MalformedURLException;
import java.net.URL;

import rocks.bottery.bot.AttachmentType;
import rocks.bottery.bot.ContextBase;
import rocks.bottery.bot.IAttachment;
import sx.blah.discord.handle.obj.IMessage.Attachment;

/**
 * Attachment definition for discord
 * 
 * @author Harald Kuhn
 */
public class DiscordAttachment extends ContextBase implements IAttachment {

	private static final long serialVersionUID = 1L;
	private Attachment		  attachment;

	public DiscordAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IAttachment#getType()
	 */
	@Override
	public AttachmentType getType() {
		return AttachmentType.FILE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IAttachment#getSize()
	 */
	@Override
	public long getSize() {
		return attachment.getFilesize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IAttachment#getName()
	 */
	@Override
	public String getName() {
		return attachment.getFilename();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IAttachment#getId()
	 */
	@Override
	public String getId() {
		return attachment.getStringID();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IAttachment#getUrl()
	 */
	@Override
	public URL getUrl() {
		try {
			return new URL(attachment.getUrl());
		}
		catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
}
