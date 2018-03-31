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
 * A Hero card (card with a single, large image)
 **/

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
 @XmlType(name = "HeroCard", propOrder =
	{ "title", "subtitle", "text", "images", "buttons", "tap"
})

@XmlRootElement(name="HeroCard")
public class HeroCard  {
  

  @XmlElement(name="title")
  private String title = null;

  @XmlElement(name="subtitle")
  private String subtitle = null;

  @XmlElement(name="text")
  private String text = null;

  @XmlElement(name="images")
  private List<CardImage> images = new ArrayList<CardImage>();

  @XmlElement(name="buttons")
  private List<CardAction> buttons = new ArrayList<CardAction>();

  @XmlElement(name="tap")
  private CardAction tap = null;

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
   * Text for the card
   **/
  
  public String getText() {
    return text;
  }
  public void setText(String text) {
    this.text = text;
  }
  /**
   * Array of images for the card
   **/
  
  public List<CardImage> getImages() {
    return images;
  }
  public void setImages(List<CardImage> images) {
    this.images = images;
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
   * This action will be activated when user taps on the card itself
   **/
  
  public CardAction getTap() {
    return tap;
  }
  public void setTap(CardAction tap) {
    this.tap = tap;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HeroCard {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    subtitle: ").append(toIndentedString(subtitle)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
    sb.append("    images: ").append(toIndentedString(images)).append("\n");
    sb.append("    buttons: ").append(toIndentedString(buttons)).append("\n");
    sb.append("    tap: ").append(toIndentedString(tap)).append("\n");
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

