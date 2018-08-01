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
package rocks.bottery.bot.resolver.mustache;

import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import rocks.bottery.bot.IContext;
import rocks.bottery.bot.IVariableResolver;

/**
 * A see https://github.com/spullara/mustache.java
 * 
 * @author Harald Kuhn
 *
 */
public class MustacheVariableResolver implements IVariableResolver {

	@Override
	public String resolveVariables(String text, IContext session) {
		return resolveVariables(text, session.asAttributeMap());
	}

	@Override
	public String resolveVariables(String text, Map<String, Serializable> variables) {

		StringWriter writer = new StringWriter();
		MustacheFactory mf = new DefaultMustacheFactory();
		Mustache mustache = mf.compile(new StringReader(text), text);
		mustache.execute(writer, variables);
		writer.flush();
		return writer.toString();
	}
}
