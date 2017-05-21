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
package rocks.bottery.bot.dialogs;

import java.io.Serializable;

import rocks.bottery.bot.util.IModel;
import rocks.bottery.bot.util.Model;

/**
 * @author Harald Kuhn
 *
 */
public class Choice<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private T				  value;
	private IModel<String>	  label;

	public Choice(T value, String label) {
		super();
		this.value = value;
		this.label = new Model<String>(label);
	}

	public Choice(T value, IModel<String> label) {
		super();
		this.value = value;
		this.label = label;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public IModel<String> getLabel() {
		return label;
	}

	public String getLabelString() {
		return label.getObject();
	}

	public void setLabel(IModel<String> label) {
		this.label = label;
	}
}