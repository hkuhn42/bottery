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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * An image on a card
 **/

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
 @XmlType(name = "CardImage", propOrder =
	{ "url", "alt", "tap"
})

@XmlRootElement(name="CardImage")
public class CardImage  {
  

  @XmlElement(name="url")
  private String url = null;

  @XmlElement(name="alt")
  private String alt = null;

  @XmlElement(name="tap")
  private CardAction tap = null;

  /**
   * URL Thumbnail image for major content property.
   **/
  
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
  /**
   * Image description intended for screen readers
   **/
  
  public String getAlt() {
    return alt;
  }
  public void setAlt(String alt) {
    this.alt = alt;
  }
  /**
   * Action assigned to specific Attachment.E.g.navigate to specific URL or play/open media content
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
    sb.append("class CardImage {\n");
    
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    alt: ").append(toIndentedString(alt)).append("\n");
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

