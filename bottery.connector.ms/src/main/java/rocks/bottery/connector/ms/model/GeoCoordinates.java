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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * GeoCoordinates (entity type: \&quot;https://schema.org/GeoCoordinates\&quot;)
 **/

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
 @XmlType(name = "GeoCoordinates", propOrder =
	{ "elevation", "latitude", "longitude", "type", "name"
})

@XmlRootElement(name="GeoCoordinates")
public class GeoCoordinates  {
  

  @XmlElement(name="elevation")
  private Double elevation = null;

  @XmlElement(name="latitude")
  private Double latitude = null;

  @XmlElement(name="longitude")
  private Double longitude = null;

  @XmlElement(name="type")
  private String type = null;

  @XmlElement(name="name")
  private String name = null;

  /**
   * Elevation of the location [WGS 84](https://en.wikipedia.org/wiki/World_Geodetic_System)
   **/
  
  public Double getElevation() {
    return elevation;
  }
  public void setElevation(Double elevation) {
    this.elevation = elevation;
  }
  /**
   * Latitude of the location [WGS 84](https://en.wikipedia.org/wiki/World_Geodetic_System)
   **/
  
  public Double getLatitude() {
    return latitude;
  }
  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }
  /**
   * Longitude of the location [WGS 84](https://en.wikipedia.org/wiki/World_Geodetic_System)
   **/
  
  public Double getLongitude() {
    return longitude;
  }
  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }
  /**
   * The type of the thing
   **/
  
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  /**
   * The name of the thing
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
    sb.append("class GeoCoordinates {\n");
    
    sb.append("    elevation: ").append(toIndentedString(elevation)).append("\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

