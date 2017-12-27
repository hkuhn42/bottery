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
package rocks.bottery.connector;

import java.net.URL;

import rocks.bottery.bot.AttachmentType;
import rocks.bottery.bot.IAttachment;

/**
 * @author Harald Kuhn
 *
 */
public class GenericAttachment implements IAttachment {

	private static final long serialVersionUID = 1L;

	private AttachmentType	  type;
	private long			  size;
	private String			  id;
	private String			  name;
	private URL				  url;

	public GenericAttachment() {
	}

	@Override
	public AttachmentType getType() {
		return type;
	}

	@Override
	public long getSize() {
		return size;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public URL getUrl() {
		return url;
	}

	public void setType(AttachmentType type) {
		this.type = type;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

}
