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
package rocks.bottery.bot.i18n;

import java.util.HashMap;
import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Harald Kuhn
 *
 */
public class BundleLocalizer implements ILocalizer {
    
    private String                      baseName;
    private Map<Locale, ResourceBundle> bundles;
    
    public BundleLocalizer(String botClazz) {
        this.baseName = botClazz;
        this.bundles = new HashMap<>();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see rocks.bottery.bot.i18n.ILocalizer#getString(java.util.Locale, java.lang.String, java.lang.Object[])
     */
    @Override
    public String getString(Locale locale, String key, Object... params) {
        String value = getString(locale, key);
        // FIXME: do we need plaint old params support?
        // if (params.length > 0) {
        //
        // }
        return value;
    }
    
    @Override
    public String getString(Locale locale, String key) {
        ResourceBundle bundle = bundles.get(locale);
        if (bundle == null) {
            try {
                bundle = ResourceBundle.getBundle(baseName, locale);
                bundles.put(locale, bundle);
            }
            catch (MissingResourceException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                bundle = new ListResourceBundle() {
                    
                    @Override
                    protected Object[][] getContents() {
                        return new Object[][] {
                                { "", "" }
                        };
                    }
                };
            }
        }
        return bundle.getString(key);
    }
}
