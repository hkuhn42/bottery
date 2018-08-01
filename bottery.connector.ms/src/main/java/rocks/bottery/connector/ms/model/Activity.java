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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * An Activity is the basic communication type for the Bot Framework 3.0 protocol
 **/

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
 @XmlType(name = "Activity", propOrder =
	{ "type", "id", "timestamp", "localTimestamp", "serviceUrl", "channelId", "from", "conversation", "recipient", "textFormat", "attachmentLayout", "membersAdded", "membersRemoved", "topicName", "historyDisclosed", "locale", "text", "summary", "attachments", "entities", "channelData", "action", "replyToId", "value"
})

@XmlRootElement(name="Activity")
public class Activity  {
  

  @XmlElement(name="type")
  private String type = null;

  @XmlElement(name="id")
  private String id = null;

  @XmlElement(name="timestamp")
  private javax.xml.datatype.XMLGregorianCalendar timestamp = null;

  @XmlElement(name="localTimestamp")
  private javax.xml.datatype.XMLGregorianCalendar localTimestamp = null;

  @XmlElement(name="serviceUrl")
  private String serviceUrl = null;

  @XmlElement(name="channelId")
  private String channelId = null;

  @XmlElement(name="from")
  private ChannelAccount from = null;

  @XmlElement(name="conversation")
  private ConversationAccount conversation = null;

  @XmlElement(name="recipient")
  private ChannelAccount recipient = null;

  @XmlElement(name="textFormat")
  private String textFormat = null;

  @XmlElement(name="attachmentLayout")
  private String attachmentLayout = null;

  @XmlElement(name="membersAdded")
  private List<ChannelAccount> membersAdded = new ArrayList<ChannelAccount>();

  @XmlElement(name="membersRemoved")
  private List<ChannelAccount> membersRemoved = new ArrayList<ChannelAccount>();

  @XmlElement(name="topicName")
  private String topicName = null;

  @XmlElement(name="historyDisclosed")
  private Boolean historyDisclosed = null;

  @XmlElement(name="locale")
  private String locale = null;

  @XmlElement(name="text")
  private String text = null;

  @XmlElement(name="summary")
  private String summary = null;

  @XmlElement(name="attachments")
  private List<Attachment> attachments = new ArrayList<Attachment>();

  @XmlElement(name="entities")
  private List<Entity> entities = new ArrayList<Entity>();

  @XmlElement(name="channelData")
  private Object channelData = null;

  @XmlElement(name="action")
  private String action = null;

  @XmlElement(name="replyToId")
  private String replyToId = null;

  @XmlElement(name="value")
  private Object value = null;

  /**
   * The type of the activity [message|contactRelationUpdate|converationUpdate|typing]
   **/
  
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  /**
   * Id for the activity
   **/
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  /**
   * UTC Time when message was sent (Set by service)
   **/
  
  public javax.xml.datatype.XMLGregorianCalendar getTimestamp() {
    return timestamp;
  }
  public void setTimestamp(javax.xml.datatype.XMLGregorianCalendar timestamp) {
    this.timestamp = timestamp;
  }
  /**
   * Local time when message was sent (set by client Ex: 2016-09-23T13:07:49.4714686-07:00)
   **/
  
  public javax.xml.datatype.XMLGregorianCalendar getLocalTimestamp() {
    return localTimestamp;
  }
  public void setLocalTimestamp(javax.xml.datatype.XMLGregorianCalendar localTimestamp) {
    this.localTimestamp = localTimestamp;
  }
  /**
   * Service endpoint
   **/
  
  public String getServiceUrl() {
    return serviceUrl;
  }
  public void setServiceUrl(String serviceUrl) {
    this.serviceUrl = serviceUrl;
  }
  /**
   * ChannelId the activity was on
   **/
  
  public String getChannelId() {
    return channelId;
  }
  public void setChannelId(String channelId) {
    this.channelId = channelId;
  }
  /**
   * Sender address
   **/
  
  public ChannelAccount getFrom() {
    return from;
  }
  public void setFrom(ChannelAccount from) {
    this.from = from;
  }
  /**
   * Conversation
   **/
  
  public ConversationAccount getConversation() {
    return conversation;
  }
  public void setConversation(ConversationAccount conversation) {
    this.conversation = conversation;
  }
  /**
   * (Outbound to bot only) Bot's address that received the message
   **/
  
  public ChannelAccount getRecipient() {
    return recipient;
  }
  public void setRecipient(ChannelAccount recipient) {
    this.recipient = recipient;
  }
  /**
   * Format of text fields [plain|markdown] Default:markdown
   **/
  
  public String getTextFormat() {
    return textFormat;
  }
  public void setTextFormat(String textFormat) {
    this.textFormat = textFormat;
  }
  /**
   * AttachmentLayout - hint for how to deal with multiple attachments Values: [list|carousel] Default:list
   **/
  
  public String getAttachmentLayout() {
    return attachmentLayout;
  }
  public void setAttachmentLayout(String attachmentLayout) {
    this.attachmentLayout = attachmentLayout;
  }
  /**
   * Array of address added
   **/
  
  public List<ChannelAccount> getMembersAdded() {
    return membersAdded;
  }
  public void setMembersAdded(List<ChannelAccount> membersAdded) {
    this.membersAdded = membersAdded;
  }
  /**
   * Array of addresses removed
   **/
  
  public List<ChannelAccount> getMembersRemoved() {
    return membersRemoved;
  }
  public void setMembersRemoved(List<ChannelAccount> membersRemoved) {
    this.membersRemoved = membersRemoved;
  }
  /**
   * Conversations new topic name
   **/
  
  public String getTopicName() {
    return topicName;
  }
  public void setTopicName(String topicName) {
    this.topicName = topicName;
  }
  /**
   * the previous history of the channel was disclosed
   **/
  
  public Boolean getHistoryDisclosed() {
    return historyDisclosed;
  }
  public void setHistoryDisclosed(Boolean historyDisclosed) {
    this.historyDisclosed = historyDisclosed;
  }
  /**
   * The language code of the Text field
   **/
  
  public String getLocale() {
    return locale;
  }
  public void setLocale(String locale) {
    this.locale = locale;
  }
  /**
   * Content for the message
   **/
  
  public String getText() {
    return text;
  }
  public void setText(String text) {
    this.text = text;
  }
  /**
   * Text to display if you can't render cards
   **/
  
  public String getSummary() {
    return summary;
  }
  public void setSummary(String summary) {
    this.summary = summary;
  }
  /**
   * Attachments
   **/
  
  public List<Attachment> getAttachments() {
    return attachments;
  }
  public void setAttachments(List<Attachment> attachments) {
    this.attachments = attachments;
  }
  /**
   * Collection of Entity objects, each of which contains metadata about this activity. Each Entity object is typed.
   **/
  
  public List<Entity> getEntities() {
    return entities;
  }
  public void setEntities(List<Entity> entities) {
    this.entities = entities;
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
  /**
   * ContactAdded/Removed action
   **/
  
  public String getAction() {
    return action;
  }
  public void setAction(String action) {
    this.action = action;
  }
  /**
   * the original id this message is a response to
   **/
  
  public String getReplyToId() {
    return replyToId;
  }
  public void setReplyToId(String replyToId) {
    this.replyToId = replyToId;
  }
  /**
   * Open ended value
   **/
  
  public Object getValue() {
    return value;
  }
  public void setValue(Object value) {
    this.value = value;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Activity {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    localTimestamp: ").append(toIndentedString(localTimestamp)).append("\n");
    sb.append("    serviceUrl: ").append(toIndentedString(serviceUrl)).append("\n");
    sb.append("    channelId: ").append(toIndentedString(channelId)).append("\n");
    sb.append("    from: ").append(toIndentedString(from)).append("\n");
    sb.append("    conversation: ").append(toIndentedString(conversation)).append("\n");
    sb.append("    recipient: ").append(toIndentedString(recipient)).append("\n");
    sb.append("    textFormat: ").append(toIndentedString(textFormat)).append("\n");
    sb.append("    attachmentLayout: ").append(toIndentedString(attachmentLayout)).append("\n");
    sb.append("    membersAdded: ").append(toIndentedString(membersAdded)).append("\n");
    sb.append("    membersRemoved: ").append(toIndentedString(membersRemoved)).append("\n");
    sb.append("    topicName: ").append(toIndentedString(topicName)).append("\n");
    sb.append("    historyDisclosed: ").append(toIndentedString(historyDisclosed)).append("\n");
    sb.append("    locale: ").append(toIndentedString(locale)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
    sb.append("    summary: ").append(toIndentedString(summary)).append("\n");
    sb.append("    attachments: ").append(toIndentedString(attachments)).append("\n");
    sb.append("    entities: ").append(toIndentedString(entities)).append("\n");
    sb.append("    channelData: ").append(toIndentedString(channelData)).append("\n");
    sb.append("    action: ").append(toIndentedString(action)).append("\n");
    sb.append("    replyToId: ").append(toIndentedString(replyToId)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

