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
 * An item on a receipt card
 **/

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
 @XmlType(name = "ReceiptItem", propOrder =
	{ "title", "subtitle", "text", "image", "price", "quantity", "tap"
})

@XmlRootElement(name="ReceiptItem")
public class ReceiptItem  {
  

  @XmlElement(name="title")
  private String title = null;

  @XmlElement(name="subtitle")
  private String subtitle = null;

  @XmlElement(name="text")
  private String text = null;

  @XmlElement(name="image")
  private CardImage image = null;

  @XmlElement(name="price")
  private String price = null;

  @XmlElement(name="quantity")
  private String quantity = null;

  @XmlElement(name="tap")
  private CardAction tap = null;

  /**
   * Title of the Card
   **/
  
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  /**
   * Subtitle appears just below Title field, differs from Title in font styling only
   **/
  
  public String getSubtitle() {
    return subtitle;
  }
  public void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }
  /**
   * Text field appears just below subtitle, differs from Subtitle in font styling only
   **/
  
  public String getText() {
    return text;
  }
  public void setText(String text) {
    this.text = text;
  }
  /**
   * Image
   **/
  
  public CardImage getImage() {
    return image;
  }
  public void setImage(CardImage image) {
    this.image = image;
  }
  /**
   * Amount with currency
   **/
  
  public String getPrice() {
    return price;
  }
  public void setPrice(String price) {
    this.price = price;
  }
  /**
   * Number of items of given kind
   **/
  
  public String getQuantity() {
    return quantity;
  }
  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }
  /**
   * This action will be activated when user taps on the Item bubble.
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
    sb.append("class ReceiptItem {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    subtitle: ").append(toIndentedString(subtitle)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
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

