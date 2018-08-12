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
/**
 * 
 */
package rocks.bottery.examples;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IAttachment;
import rocks.bottery.bot.ISession;
import rocks.bottery.bot.dialogs.Utterance;
import rocks.bottery.bot.universal.UniversalBot;
import rocks.bottery.bot.util.IModel;
import rocks.bottery.bot.util.Model;;

/**
 * A basic echo example bot
 * 
 * @author Harald Kuhn
 */
public class AttaBot extends UniversalBot {

	public AttaBot() {
		setWelcomeDialog(new Utterance() {
			@Override
			public IModel<String> getText(IActivity request, ISession session) {
				if (request.getAttachments().isEmpty()) {
					return new Model<String>("No attachments");
				}
				else {
					StringBuilder builder = new StringBuilder("Thanks for: ");
					for (IAttachment atta : request.getAttachments()) {
						File newFile = new File("c:/temp/" + atta.getName());
						newFile.mkdirs();
						try (InputStream urlStream = atta.getUrl().openStream(); OutputStream fileStream = new FileOutputStream(newFile)) {
							IOUtils.copy(urlStream, fileStream);
						}
						catch (IOException e) {
							e.printStackTrace();
						}

						builder.append("\n-" + atta.getName());
						builder.append("\t(");
						for (String name : atta.getAttributeNames()) {
							builder.append(name + ":" + atta.getAttribute(name) + ";");
						}
						builder.append(")");

					}
					return new Model<String>(builder.toString());
				}

			}
		});
	}
}
