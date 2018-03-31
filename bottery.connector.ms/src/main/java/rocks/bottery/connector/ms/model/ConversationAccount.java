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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Channel account information for a conversation
 **/

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
 @XmlType(name = "ConversationAccount", propOrder =
	{ "isGroup", "id", "name"
})

@XmlRootElement(name="ConversationAccount")
public class ConversationAccount  {
  

  @XmlElement(name="isGroup")
  private Boolean isGroup = null;

  @XmlElement(name="id")
  private String id = null;

  @XmlElement(name="name")
  private String name = null;

  /**
   * Is this a reference to a group
   **/
  
  public Boolean getIsGroup() {
    return isGroup;
  }
  public void setIsGroup(Boolean isGroup) {
    this.isGroup = isGroup;
  }
  /**
   * Channel id for the user or bot on this channel (Example: joe@smith.com, or @joesmith or 123456)
   **/
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  /**
   * Display friendly name
   **/
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConversationAccount {\n");
    
    sb.append("    isGroup: ").append(toIndentedString(isGroup)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

