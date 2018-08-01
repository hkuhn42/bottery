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
package rocks.bottery.bot.recognizers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rocks.bottery.bot.ContextBase;

/**
 * Basic intent for a command word or intent name
 * 
 * @author Harald Kuhn
 */
public class CommandIntent extends ContextBase implements IIntent, Serializable {

	private static final long serialVersionUID = 1L;

	private String			  intent;
	private String			  responseSuggestion;
	private boolean			  fullfillment;

	public CommandIntent(String intent) {
		this.intent = intent;
	}

	public CommandIntent(String intent, String responseSuggestion) {
		this.intent = intent;
		this.responseSuggestion = responseSuggestion;
	}

	@Override
	public String getIntentName() {
		return intent;
	}

	@Override
	public Object getRecognizerIntent() {
		return intent;
	}

	@Override
	public String getResponseSuggestion() {
		return responseSuggestion;
	}

	@Override
	public double getConfidence() {
		return 1;
	}

	@Override
	public List<IEntity> getEntities() {
		List<IEntity> entities = new ArrayList<>();
		for (Map.Entry<String, Serializable> attribute : asAttributeMap().entrySet()) {
			if (attribute.getValue() instanceof IEntity) {
				entities.add((IEntity) attribute.getValue());
			}
		}
		return entities;
	}

	@Override
	public IEntity getEntity(String name) {
		Serializable possibleEntity = getAttribute(name);
		if (possibleEntity instanceof IEntity) {
			return (IEntity) possibleEntity;
		}
		return null;
	}

	public boolean isFullfillment() {
		return fullfillment;
	}

	public void setFullfillment(boolean fullfillment) {
		this.fullfillment = fullfillment;
	}
}