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
 * A receipt card
 **/

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
 @XmlType(name = "ReceiptCard", propOrder =
	{ "title", "items", "facts", "tap", "total", "tax", "vat", "buttons"
})

@XmlRootElement(name="ReceiptCard")
public class ReceiptCard  {
  

  @XmlElement(name="title")
  private String title = null;

  @XmlElement(name="items")
  private List<ReceiptItem> items = new ArrayList<ReceiptItem>();

  @XmlElement(name="facts")
  private List<Fact> facts = new ArrayList<Fact>();

  @XmlElement(name="tap")
  private CardAction tap = null;

  @XmlElement(name="total")
  private String total = null;

  @XmlElement(name="tax")
  private String tax = null;

  @XmlElement(name="vat")
  private String vat = null;

  @XmlElement(name="buttons")
  private List<CardAction> buttons = new ArrayList<CardAction>();

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
   * Array of Receipt Items
   **/
  
  public List<ReceiptItem> getItems() {
    return items;
  }
  public void setItems(List<ReceiptItem> items) {
    this.items = items;
  }
  /**
   * Array of Fact Objects   Array of key-value pairs.
   **/
  
  public List<Fact> getFacts() {
    return facts;
  }
  public void setFacts(List<Fact> facts) {
    this.facts = facts;
  }
  /**
   * This action will be activated when user taps on the card
   **/
  
  public CardAction getTap() {
    return tap;
  }
  public void setTap(CardAction tap) {
    this.tap = tap;
  }
  /**
   * Total amount of money paid (or should be paid)
   **/
  
  public String getTotal() {
    return total;
  }
  public void setTotal(String total) {
    this.total = total;
  }
  /**
   * Total amount of TAX paid(or should be paid)
   **/
  
  public String getTax() {
    return tax;
  }
  public void setTax(String tax) {
    this.tax = tax;
  }
  /**
   * Total amount of VAT paid(or should be paid)
   **/
  
  public String getVat() {
    return vat;
  }
  public void setVat(String vat) {
    this.vat = vat;
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

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReceiptCard {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
    sb.append("    facts: ").append(toIndentedString(facts)).append("\n");
    sb.append("    tap: ").append(toIndentedString(tap)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    tax: ").append(toIndentedString(tax)).append("\n");
    sb.append("    vat: ").append(toIndentedString(vat)).append("\n");
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

