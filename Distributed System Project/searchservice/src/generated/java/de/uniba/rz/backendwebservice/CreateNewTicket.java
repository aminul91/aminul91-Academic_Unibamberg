
package de.uniba.rz.backendwebservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createNewTicket complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createNewTicket"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="reporter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="topic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="typeOrdinal" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="priorityOrdinal" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createNewTicket", propOrder = {
    "reporter",
    "topic",
    "description",
    "typeOrdinal",
    "priorityOrdinal"
})
public class CreateNewTicket {

    protected String reporter;
    protected String topic;
    protected String description;
    protected int typeOrdinal;
    protected int priorityOrdinal;

    /**
     * Gets the value of the reporter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReporter() {
        return reporter;
    }

    /**
     * Sets the value of the reporter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReporter(String value) {
        this.reporter = value;
    }

    /**
     * Gets the value of the topic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Sets the value of the topic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTopic(String value) {
        this.topic = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the typeOrdinal property.
     * 
     */
    public int getTypeOrdinal() {
        return typeOrdinal;
    }

    /**
     * Sets the value of the typeOrdinal property.
     * 
     */
    public void setTypeOrdinal(int value) {
        this.typeOrdinal = value;
    }

    /**
     * Gets the value of the priorityOrdinal property.
     * 
     */
    public int getPriorityOrdinal() {
        return priorityOrdinal;
    }

    /**
     * Sets the value of the priorityOrdinal property.
     * 
     */
    public void setPriorityOrdinal(int value) {
        this.priorityOrdinal = value;
    }

}
