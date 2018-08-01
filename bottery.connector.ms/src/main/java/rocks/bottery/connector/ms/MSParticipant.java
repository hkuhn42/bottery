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

import rocks.bottery.bot.IParticipant;
import rocks.bottery.connector.ms.model.ChannelAccount;

/**
 * The participant for the msConnector
 * 
 * {@link #getConnectorParticipant()} gives access to the ChannelAccount
 * 
 * @author Harald Kuhn
 */
public class MSParticipant implements IParticipant {
	private ChannelAccount account;

	MSParticipant(ChannelAccount account) {
		this.account = account;
	}

	ChannelAccount getChannelAccount() {
		return account;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IParticipant#getId()
	 */
	@Override
	public String getId() {
		return account.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sylvani.bot.IParticipant#getName()
	 */
	@Override
	public String getName() {
		return account.getName();
	}

	@Override
	public String getChannel() {
		return "ms";
	}

	@Override
	public Object getConnectorParticipant() {
		return account;
	}

	@Override
	public String getAddress() {
		return account.getId();
	}
}
