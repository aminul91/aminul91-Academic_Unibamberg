
package searchservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the searchservice package. 
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

    private final static QName _GetTickets_QNAME = new QName("http://searchservice/", "getTickets");
    private final static QName _GetTicketsByName_QNAME = new QName("http://searchservice/", "getTicketsByName");
    private final static QName _GetTicketsByNameAndType_QNAME = new QName("http://searchservice/", "getTicketsByNameAndType");
    private final static QName _GetTicketsByNameAndTypeResponse_QNAME = new QName("http://searchservice/", "getTicketsByNameAndTypeResponse");
    private final static QName _GetTicketsByNameResponse_QNAME = new QName("http://searchservice/", "getTicketsByNameResponse");
    private final static QName _GetTicketsResponse_QNAME = new QName("http://searchservice/", "getTicketsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: searchservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetTickets }
     * 
     */
    public GetTickets createGetTickets() {
        return new GetTickets();
    }

    /**
     * Create an instance of {@link GetTicketsByName }
     * 
     */
    public GetTicketsByName createGetTicketsByName() {
        return new GetTicketsByName();
    }

    /**
     * Create an instance of {@link GetTicketsByNameAndType }
     * 
     */
    public GetTicketsByNameAndType createGetTicketsByNameAndType() {
        return new GetTicketsByNameAndType();
    }

    /**
     * Create an instance of {@link GetTicketsByNameAndTypeResponse }
     * 
     */
    public GetTicketsByNameAndTypeResponse createGetTicketsByNameAndTypeResponse() {
        return new GetTicketsByNameAndTypeResponse();
    }

    /**
     * Create an instance of {@link GetTicketsByNameResponse }
     * 
     */
    public GetTicketsByNameResponse createGetTicketsByNameResponse() {
        return new GetTicketsByNameResponse();
    }

    /**
     * Create an instance of {@link GetTicketsResponse }
     * 
     */
    public GetTicketsResponse createGetTicketsResponse() {
        return new GetTicketsResponse();
    }

    /**
     * Create an instance of {@link Ticket }
     * 
     */
    public Ticket createTicket() {
        return new Ticket();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTickets }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetTickets }{@code >}
     */
    @XmlElementDecl(namespace = "http://searchservice/", name = "getTickets")
    public JAXBElement<GetTickets> createGetTickets(GetTickets value) {
        return new JAXBElement<GetTickets>(_GetTickets_QNAME, GetTickets.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTicketsByName }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetTicketsByName }{@code >}
     */
    @XmlElementDecl(namespace = "http://searchservice/", name = "getTicketsByName")
    public JAXBElement<GetTicketsByName> createGetTicketsByName(GetTicketsByName value) {
        return new JAXBElement<GetTicketsByName>(_GetTicketsByName_QNAME, GetTicketsByName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTicketsByNameAndType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetTicketsByNameAndType }{@code >}
     */
    @XmlElementDecl(namespace = "http://searchservice/", name = "getTicketsByNameAndType")
    public JAXBElement<GetTicketsByNameAndType> createGetTicketsByNameAndType(GetTicketsByNameAndType value) {
        return new JAXBElement<GetTicketsByNameAndType>(_GetTicketsByNameAndType_QNAME, GetTicketsByNameAndType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTicketsByNameAndTypeResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetTicketsByNameAndTypeResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://searchservice/", name = "getTicketsByNameAndTypeResponse")
    public JAXBElement<GetTicketsByNameAndTypeResponse> createGetTicketsByNameAndTypeResponse(GetTicketsByNameAndTypeResponse value) {
        return new JAXBElement<GetTicketsByNameAndTypeResponse>(_GetTicketsByNameAndTypeResponse_QNAME, GetTicketsByNameAndTypeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTicketsByNameResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetTicketsByNameResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://searchservice/", name = "getTicketsByNameResponse")
    public JAXBElement<GetTicketsByNameResponse> createGetTicketsByNameResponse(GetTicketsByNameResponse value) {
        return new JAXBElement<GetTicketsByNameResponse>(_GetTicketsByNameResponse_QNAME, GetTicketsByNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTicketsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetTicketsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://searchservice/", name = "getTicketsResponse")
    public JAXBElement<GetTicketsResponse> createGetTicketsResponse(GetTicketsResponse value) {
        return new JAXBElement<GetTicketsResponse>(_GetTicketsResponse_QNAME, GetTicketsResponse.class, null, value);
    }

}
