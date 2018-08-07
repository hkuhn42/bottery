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
package rocks.bottery.connector;

/**
 * Enum representing the channels and some of their capabilities
 * 
 * @author Harald Kuhn
 */
public enum Channel {

	FACEBOOK("facebook"),

	SYKPE("skype"),

	TELEGRAM("telegram"),

	KIK("kik"),

	EMAIL("email"),

	SLACK("slack"),

	GROUPME("groupme"),

	SMS("sms"),

	EMULATOR("emulator"),

	DIRECTLINE("directline"),

	CONSOLE("console"),

	DISCORD("discord"),

	GITTER("gitter");

	private String id;

	Channel(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return id;
	}

	public boolean supportsKeyboards(Channel channel) {
		return supportsKeyboards(channel, 100);
	}

	/**
	 * Wether the given channel supports a keyboard
	 */
	public boolean supportsKeyboards(Channel channel, int buttonCnt) {
		boolean supportsKeyboards = false;
		switch (channel) {
			case FACEBOOK:
				supportsKeyboards = (buttonCnt <= 10);
				break;
			case KIK:
				supportsKeyboards = (buttonCnt <= 20);
				break;
			case TELEGRAM:
			case EMULATOR:
				supportsKeyboards = (buttonCnt <= 100);
				break;
			default:
				break;

		}
		return supportsKeyboards;
	}

	/*
	 * export function supportsCardActions(session: Session, buttonCnt = 100) { switch (getChannelId(session)) { case
	 * channels.facebook: case channels.skype: return (buttonCnt <= 3); case channels.slack: return (buttonCnt <= 100);
	 * default: return false; } }
	 * 
	 */
	public boolean supportsCardActions(Channel channel) {
		return supportsCardActions(channel, 100);
	}

	public boolean supportsCardActions(Channel channel, int cardCnt) {
		boolean supportsCardActions = false;
		switch (channel) {
			case FACEBOOK:
			case SYKPE:
				supportsCardActions = (cardCnt <= 3);
				break;
			case SLACK:
				supportsCardActions = (cardCnt <= 100);
			default:
				break;
		}
		return supportsCardActions;
	}

}
