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
package rocks.bottery.connector.ms.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
{ 
    "issuer":"https://api.botframework.com",
    "authorization_endpoint":"https://invalid.botframework.com/",
    "jwks_uri":"https://login.botframework.com/v1/keys",
    "id_token_signing_alg_values_supported":["RSA256"],
    "token_endpoint_auth_methods_supported":["private_key_jwt"]
}
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OpenIdConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "issuer")
	private String issuer;
	@XmlElement(name = "authorization_endpoint")
	private String authorizationEndpoint;
	@XmlElement(name = "jwks_uri")
	private String jwksUri;
	@XmlElement(name = "id_token_signing_alg_values_supported")
	private List<String> idTokenSigningAlgValuesSupported = null;
	@XmlElement(name = "token_endpoint_auth_methods_supported")
	private List<String> tokenEndpointAuthMethodsSupported = null;

	/**
	 *
	 * @return The issuer
	 */
	public String getIssuer() {
		return issuer;
	}

	/**
	 *
	 * @param issuer
	 *            The issuer
	 */
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	/**
	 *
	 * @return The authorizationEndpoint
	 */
	public String getAuthorizationEndpoint() {
		return authorizationEndpoint;
	}

	/**
	 *
	 * @param authorizationEndpoint
	 *            The authorization_endpoint
	 */
	public void setAuthorizationEndpoint(String authorizationEndpoint) {
		this.authorizationEndpoint = authorizationEndpoint;
	}

	/**
	 *
	 * @return The jwksUri
	 */
	public String getJwksUri() {
		return jwksUri;
	}

	/**
	 *
	 * @param jwksUri
	 *            The jwks_uri
	 */
	public void setJwksUri(String jwksUri) {
		this.jwksUri = jwksUri;
	}

	/**
	 *
	 * @return The idTokenSigningAlgValuesSupported
	 */
	public List<String> getIdTokenSigningAlgValuesSupported() {
		return idTokenSigningAlgValuesSupported;
	}

	/**
	 *
	 * @param idTokenSigningAlgValuesSupported
	 *            The id_token_signing_alg_values_supported
	 */
	public void setIdTokenSigningAlgValuesSupported(List<String> idTokenSigningAlgValuesSupported) {
		this.idTokenSigningAlgValuesSupported = idTokenSigningAlgValuesSupported;
	}

	/**
	 *
	 * @return The tokenEndpointAuthMethodsSupported
	 */
	public List<String> getTokenEndpointAuthMethodsSupported() {
		return tokenEndpointAuthMethodsSupported;
	}

	/**
	 *
	 * @param tokenEndpointAuthMethodsSupported
	 *            The token_endpoint_auth_methods_supported
	 */
	public void setTokenEndpointAuthMethodsSupported(List<String> tokenEndpointAuthMethodsSupported) {
		this.tokenEndpointAuthMethodsSupported = tokenEndpointAuthMethodsSupported;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class OpenIdConfig {\n");

		sb.append("    issuer: ").append(toIndentedString(issuer)).append("\n");
		sb.append("    authorization_endpoint: ").append(toIndentedString(authorizationEndpoint)).append("\n");
		sb.append("    jwksUri: ").append(toIndentedString(jwksUri)).append("\n");
		sb.append("    tokenEndpointAuthMethodsSupported: ")
				.append(toIndentedString(this.tokenEndpointAuthMethodsSupported)).append("\n");
		sb.append("    id_token_signing_alg_values_supported: ")
				.append(toIndentedString(this.idTokenSigningAlgValuesSupported)).append("\n");

		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private static String toIndentedString(Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
