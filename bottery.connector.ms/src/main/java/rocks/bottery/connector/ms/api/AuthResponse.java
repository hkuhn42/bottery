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
package rocks.bottery.connector.ms.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Harald Kuhn
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AuthResponse {

	@XmlElement(name = "token_type")
	private String tokenType;

	@XmlElement(name = "expires_in")
	private int expiresIn;

	@XmlElement(name = "ext_expires_in")
	private String extExpiresIn;

	@XmlElement(name = "access_token")
	private String accessToken;

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expires_in) {
		this.expiresIn = expires_in;
	}

	public String getExtExpiresIn() {
		return extExpiresIn;
	}

	public void setExtExpiresIn(String extExpiresIn) {
		this.extExpiresIn = extExpiresIn;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
