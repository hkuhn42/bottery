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
package rocks.bottery.connector.ms.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Parameters for creating a new conversation
 **/

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
 @XmlType(name = "ConversationParameters", propOrder =
	{ "isGroup", "bot", "members", "topicName", "activity", "channelData"
})

@XmlRootElement(name="ConversationParameters")
public class ConversationParameters  {
  

  @XmlElement(name="isGroup")
  private Boolean isGroup = null;

  @XmlElement(name="bot")
  private ChannelAccount bot = null;

  @XmlElement(name="members")
  private List<ChannelAccount> members = new ArrayList<ChannelAccount>();

  @XmlElement(name="topicName")
  private String topicName = null;

  @XmlElement(name="activity")
  private Activity activity = null;

  @XmlElement(name="channelData")
  private Object channelData = null;

  /**
   * IsGroup
   **/
  
  public Boolean getIsGroup() {
    return isGroup;
  }
  public void setIsGroup(Boolean isGroup) {
    this.isGroup = isGroup;
  }
  /**
   * The bot address for this conversation
   **/
  
  public ChannelAccount getBot() {
    return bot;
  }
  public void setBot(ChannelAccount bot) {
    this.bot = bot;
  }
  /**
   * Members to add to the conversation
   **/
  
  public List<ChannelAccount> getMembers() {
    return members;
  }
  public void setMembers(List<ChannelAccount> members) {
    this.members = members;
  }
  /**
   * (Optional) Topic of the conversation (if supported by the channel)
   **/
  
  public String getTopicName() {
    return topicName;
  }
  public void setTopicName(String topicName) {
    this.topicName = topicName;
  }
  /**
   * (Optional) When creating a new conversation, use this activity as the intial message to the conversation
   **/
  
  public Activity getActivity() {
    return activity;
  }
  public void setActivity(Activity activity) {
    this.activity = activity;
  }
  /**
   * Channel specific payload
   **/
  
  public Object getChannelData() {
    return channelData;
  }
  public void setChannelData(Object channelData) {
    this.channelData = channelData;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConversationParameters {\n");
    
    sb.append("    isGroup: ").append(toIndentedString(isGroup)).append("\n");
    sb.append("    bot: ").append(toIndentedString(bot)).append("\n");
    sb.append("    members: ").append(toIndentedString(members)).append("\n");
    sb.append("    topicName: ").append(toIndentedString(topicName)).append("\n");
    sb.append("    activity: ").append(toIndentedString(activity)).append("\n");
    sb.append("    channelData: ").append(toIndentedString(channelData)).append("\n");
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

