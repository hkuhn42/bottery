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
package rocks.bottery.bot.connector.ms.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * A card representing a request to sign in
 **/

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
 @XmlType(name = "SigninCard", propOrder =
	{ "text", "buttons"
})

@XmlRootElement(name="SigninCard")
public class SigninCard  {
  

  @XmlElement(name="text")
  private String text = null;

  @XmlElement(name="buttons")
  private List<CardAction> buttons = new ArrayList<CardAction>();

  /**
   * Text for signin request
   **/
  
  public String getText() {
    return text;
  }
  public void setText(String text) {
    this.text = text;
  }
  /**
   * Action to use to perform signin
   **/
  
  public List<CardAction> getButtons() {
    return buttons;
  }
  public void setButtons(List<CardAction> buttons) {
    this.buttons = buttons;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SigninCard {\n");
    
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
    sb.append("    buttons: ").append(toIndentedString(buttons)).append("\n");
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

