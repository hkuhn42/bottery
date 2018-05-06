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
package rocks.bottery.connector.stride;

import java.io.UnsupportedEncodingException;
import java.security.Key;

import org.apache.log4j.Logger;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.ErrorCodes;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;

/**
 * Utility to validate JWTs
 * 
 * @see https://developer.atlassian.com/cloud/stride/security/jwt
 * 
 * @author Harald Kuhn
 *
 */
public class JWTUtils {

	private Key	   verificationKey = null;
	private String couldId;

	public JWTUtils(String clientSecret, String couldId) {

		try {
			verificationKey = new HmacKey(clientSecret.getBytes("UTF-8"));
		}
		catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}

	public boolean validate(String jwt) {

		jwt = jwt.substring(7, jwt.length());
		JwtConsumer jwtConsumer = new JwtConsumerBuilder().setRequireExpirationTime() // expire if over but
		        .setAllowedClockSkewInSeconds(15) // clocks my not be in sync, internet takes time to deliver
		        .setExpectedIssuer(couldId) // Issue is cloud id
		        .setVerificationKey(verificationKey)// the key (from client secret)
		        .setJwsAlgorithmConstraints( // only allow the expected signature algorithm(s) in the given context
		                new AlgorithmConstraints(ConstraintType.WHITELIST, // which is HMAC_SHA256 here
		                        AlgorithmIdentifiers.HMAC_SHA256))
		        .build();

		try {
			// Validate the JWT and process it to the Claims
			JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
			Logger.getLogger(JWTUtils.class).debug("JWT isValid " + jwtClaims);
			return true;
		}
		catch (InvalidJwtException e) {
			try {
				Logger.getLogger(JWTUtils.class).info("JWT failed" + e);
				if (e.hasExpired()) {
					Logger.getLogger(JWTUtils.class).info("JWT expired at " + e.getJwtContext().getJwtClaims().getExpirationTime());
				}
				if (e.hasErrorCode(ErrorCodes.AUDIENCE_INVALID)) {
					Logger.getLogger(JWTUtils.class).info("JWT had wrong audience: " + e.getJwtContext().getJwtClaims().getAudience());
				}
			}
			catch (MalformedClaimException e1) {
				Logger.getLogger(JWTUtils.class).info("MalformedClaims: ", e);
			}
			return false;
		}

	}
}
