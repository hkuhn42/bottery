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
package rocks.bottery.bot;

import java.io.Serializable;
import java.net.URL;

/**
 * An attachment to an activity like a photo video or audio message.
 * 
 * Depending on the connector different types of attachments may be supported. If the connector implementation offers
 * additional metadata, these are delivered via attributes.
 * 
 * @author Harald Kuhn
 */
public interface IAttachment extends IContext, Serializable {

	/**
	 * Key of the IMAGE_WIDTH attribute
	 */
	public static String IMAGE_WIDTH  = "imageWidth";

	/**
	 * Key of the IMAGE_HEIGHT attribute
	 */
	public static String IMAGE_HEIGHT = "imageHeight";

	/**
	 * the type of attachment
	 * 
	 * @return
	 */
	public AttachmentType getType();

	/**
	 * the size of the attachment
	 * 
	 * @return
	 */
	public long getSize();

	/**
	 * the name
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * a unique id for the attachment
	 * 
	 * @return
	 */
	public String getId();

	/**
	 * the url to lookup the attachment
	 * 
	 * @return
	 */
	public URL getUrl();
}
