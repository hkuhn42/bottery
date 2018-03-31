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
 * A audio card
 **/

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
 @XmlType(name = "AudioCard", propOrder =
	{ "aspect", "title", "subtitle", "text", "image", "media", "buttons", "shareable", "autoloop", "autostart"
})

@XmlRootElement(name="AudioCard")
public class AudioCard  {
  

  @XmlElement(name="aspect")
  private String aspect = null;

  @XmlElement(name="title")
  private String title = null;

  @XmlElement(name="subtitle")
  private String subtitle = null;

  @XmlElement(name="text")
  private String text = null;

  @XmlElement(name="image")
  private ThumbnailUrl image = null;

  @XmlElement(name="media")
  private List<MediaUrl> media = new ArrayList<MediaUrl>();

  @XmlElement(name="buttons")
  private List<CardAction> buttons = new ArrayList<CardAction>();

  @XmlElement(name="shareable")
  private Boolean shareable = null;

  @XmlElement(name="autoloop")
  private Boolean autoloop = null;

  @XmlElement(name="autostart")
  private Boolean autostart = null;

  /**
   * Aspect ratio of thumbnail/media placeholder, allowed values are \"16x9\" and \"9x16\"
   **/
  
  public String getAspect() {
    return aspect;
  }
  public void setAspect(String aspect) {
    this.aspect = aspect;
  }
  /**
   * Title of the card
   **/
  
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  /**
   * Subtitle of the card
   **/
  
  public String getSubtitle() {
    return subtitle;
  }
  public void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }
  /**
   * Text of the card
   **/
  
  public String getText() {
    return text;
  }
  public void setText(String text) {
    this.text = text;
  }
  /**
   * Thumbnail placeholder
   **/
  
  public ThumbnailUrl getImage() {
    return image;
  }
  public void setImage(ThumbnailUrl image) {
    this.image = image;
  }
  /**
   * Array of media Url objects
   **/
  
  public List<MediaUrl> getMedia() {
    return media;
  }
  public void setMedia(List<MediaUrl> media) {
    this.media = media;
  }
  /**
   * Set of actions applicable to the current card
   **/
  
  public List<CardAction> getButtons() {
    return buttons;
  }
  public void setButtons(List<CardAction> buttons) {
    this.buttons = buttons;
  }
  /**
   * Is it OK for this content to be shareable with others (default:true)
   **/
  
  public Boolean getShareable() {
    return shareable;
  }
  public void setShareable(Boolean shareable) {
    this.shareable = shareable;
  }
  /**
   * Should the client loop playback at end of content (default:true)
   **/
  
  public Boolean getAutoloop() {
    return autoloop;
  }
  public void setAutoloop(Boolean autoloop) {
    this.autoloop = autoloop;
  }
  /**
   * Should the client automatically start playback of video in this card (default:true)
   **/
  
  public Boolean getAutostart() {
    return autostart;
  }
  public void setAutostart(Boolean autostart) {
    this.autostart = autostart;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AudioCard {\n");
    
    sb.append("    aspect: ").append(toIndentedString(aspect)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    subtitle: ").append(toIndentedString(subtitle)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    media: ").append(toIndentedString(media)).append("\n");
    sb.append("    buttons: ").append(toIndentedString(buttons)).append("\n");
    sb.append("    shareable: ").append(toIndentedString(shareable)).append("\n");
    sb.append("    autoloop: ").append(toIndentedString(autoloop)).append("\n");
    sb.append("    autostart: ").append(toIndentedString(autostart)).append("\n");
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

