
package de.uniba.rz.backendwebservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.uniba.rz.backendwebservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CreateNewTicket_QNAME = new QName("http://backendWebService.rz.uniba.de/", "createNewTicket");
    private final static QName _CreateNewTicketResponse_QNAME = new QName("http://backendWebService.rz.uniba.de/", "createNewTicketResponse");
    private final static QName _GetAllTicket_QNAME = new QName("http://backendWebService.rz.uniba.de/", "getAllTicket");
    private final static QName _GetAllTicketResponse_QNAME = new QName("http://backendWebService.rz.uniba.de/", "getAllTicketResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.uniba.rz.backendwebservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateNewTicket }
     * 
     */
    public CreateNewTicket createCreateNewTicket() {
        return new CreateNewTicket();
    }

    /**
     * Create an instance of {@link CreateNewTicketResponse }
     * 
     */
    public CreateNewTicketResponse createCreateNewTicketResponse() {
        return new CreateNewTicketResponse();
    }

    /**
     * Create an instance of {@link GetAllTicket }
     * 
     */
    public GetAllTicket createGetAllTicket() {
        return new GetAllTicket();
    }

    /**
     * Create an instance of {@link GetAllTicketResponse }
     * 
     */
    public GetAllTicketResponse createGetAllTicketResponse() {
        return new GetAllTicketResponse();
    }

    /**
     * Create an instance of {@link Ticket }
     * 
     */
    public Ticket createTicket() {
        return new Ticket();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateNewTicket }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateNewTicket }{@code >}
     */
    @XmlElementDecl(namespace = "http://backendWebService.rz.uniba.de/", name = "createNewTicket")
    public JAXBElement<CreateNewTicket> createCreateNewTicket(CreateNewTicket value) {
        return new JAXBElement<CreateNewTicket>(_CreateNewTicket_QNAME, CreateNewTicket.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateNewTicketResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateNewTicketResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://backendWebService.rz.uniba.de/", name = "createNewTicketResponse")
    public JAXBElement<CreateNewTicketResponse> createCreateNewTicketResponse(CreateNewTicketResponse value) {
        return new JAXBElement<CreateNewTicketResponse>(_CreateNewTicketResponse_QNAME, CreateNewTicketResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllTicket }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllTicket }{@code >}
     */
    @XmlElementDecl(namespace = "http://backendWebService.rz.uniba.de/", name = "getAllTicket")
    public JAXBElement<GetAllTicket> createGetAllTicket(GetAllTicket value) {
        return new JAXBElement<GetAllTicket>(_GetAllTicket_QNAME, GetAllTicket.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllTicketResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllTicketResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://backendWebService.rz.uniba.de/", name = "getAllTicketResponse")
    public JAXBElement<GetAllTicketResponse> createGetAllTicketResponse(GetAllTicketResponse value) {
        return new JAXBElement<GetAllTicketResponse>(_GetAllTicketResponse_QNAME, GetAllTicketResponse.class, null, value);
    }

}
