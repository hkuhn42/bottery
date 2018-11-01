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
package rocks.bottery.connector.ms;

import java.net.MalformedURLException;
import java.net.URL;

import rocks.bottery.bot.AttachmentType;
import rocks.bottery.bot.ContextBase;
import rocks.bottery.bot.IAttachment;
import rocks.bottery.connector.ms.model.Attachment;

/**
 * Attachment wrapper for the msbot framework
 * 
 * @author Harald Kuhn
 */
public class MSAttachment extends ContextBase implements IAttachment {

	private static final long serialVersionUID = 1L;

	private Attachment		  attachment;

	public MSAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	@Override
	public AttachmentType getType() {
		return AttachmentType.FILE;
	}

	@Override
	public long getSize() {
		return -1;
	}

	@Override
	public String getName() {
		return attachment.getName();
	}

	@Override
	public String getId() {
		return String.valueOf(attachment.hashCode());
	}

	@Override
	public URL getUrl() {
		try {
			return new URL(attachment.getContentUrl());
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
}