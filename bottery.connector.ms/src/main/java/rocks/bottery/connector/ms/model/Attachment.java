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
 * An attachment within an activity
 **/
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Attachment", propOrder =
{ "contentType", "contentUrl", "content", "name", "thumbnailUrl"
})
@XmlRootElement(name = "Attachment")
public class Attachment {
    
    @XmlElement(name = "contentType")
    private String contentType  = null;
    
    @XmlElement(name = "contentUrl")
    private String contentUrl   = null;
    
    @XmlElement(name = "content")
    private Object content      = null;
    
    @XmlElement(name = "name")
    private String name         = null;
    
    @XmlElement(name = "thumbnailUrl")
    private String thumbnailUrl = null;
    
    /**
     * mimetype/Contenttype for the file
     **/
    
    public String getContentType() {
        return contentType;
    }
    
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    
    /**
     * Content Url
     **/
    
    public String getContentUrl() {
        return contentUrl;
    }
    
    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }
    
    /**
     * Embedded content
     **/
    
    public Object getContent() {
        return content;
    }
    
    public void setContent(Object content) {
        this.content = content;
    }
    
    /**
     * (OPTIONAL) The name of the attachment
     **/
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * (OPTIONAL) Thumbnail associated with attachment
     **/
    
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
    
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Attachment {\n");
        
        sb.append("    contentType: ").append(toTestIndentedString(contentType)).append("\n");
        sb.append("    contentUrl: ").append(toTestIndentedString(contentUrl)).append("\n");
        sb.append("    content: ").append(toTestIndentedString(content)).append("\n");
        sb.append("    name: ").append(toTestIndentedString(name)).append("\n");
        sb.append("    thumbnailUrl: ").append(toTestIndentedString(thumbnailUrl)).append("\n");
        sb.append("}");
        return sb.toString();
    }
    
    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private static String toTestIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
