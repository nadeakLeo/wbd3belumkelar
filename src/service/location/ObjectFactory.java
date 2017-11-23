
package service.location;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the service.location package. 
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

    private final static QName _AddLocation_QNAME = new QName("http://location.service/", "addLocation");
    private final static QName _EditLocation_QNAME = new QName("http://location.service/", "editLocation");
    private final static QName _AddLocationResponse_QNAME = new QName("http://location.service/", "addLocationResponse");
    private final static QName _EditLocationResponse_QNAME = new QName("http://location.service/", "editLocationResponse");
    private final static QName _DeleteLocationResponse_QNAME = new QName("http://location.service/", "deleteLocationResponse");
    private final static QName _DeleteLocation_QNAME = new QName("http://location.service/", "deleteLocation");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: service.location
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DeleteLocation }
     * 
     */
    public DeleteLocation createDeleteLocation() {
        return new DeleteLocation();
    }

    /**
     * Create an instance of {@link EditLocation }
     * 
     */
    public EditLocation createEditLocation() {
        return new EditLocation();
    }

    /**
     * Create an instance of {@link AddLocationResponse }
     * 
     */
    public AddLocationResponse createAddLocationResponse() {
        return new AddLocationResponse();
    }

    /**
     * Create an instance of {@link AddLocation }
     * 
     */
    public AddLocation createAddLocation() {
        return new AddLocation();
    }

    /**
     * Create an instance of {@link DeleteLocationResponse }
     * 
     */
    public DeleteLocationResponse createDeleteLocationResponse() {
        return new DeleteLocationResponse();
    }

    /**
     * Create an instance of {@link EditLocationResponse }
     * 
     */
    public EditLocationResponse createEditLocationResponse() {
        return new EditLocationResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddLocation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://location.service/", name = "addLocation")
    public JAXBElement<AddLocation> createAddLocation(AddLocation value) {
        return new JAXBElement<AddLocation>(_AddLocation_QNAME, AddLocation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditLocation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://location.service/", name = "editLocation")
    public JAXBElement<EditLocation> createEditLocation(EditLocation value) {
        return new JAXBElement<EditLocation>(_EditLocation_QNAME, EditLocation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddLocationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://location.service/", name = "addLocationResponse")
    public JAXBElement<AddLocationResponse> createAddLocationResponse(AddLocationResponse value) {
        return new JAXBElement<AddLocationResponse>(_AddLocationResponse_QNAME, AddLocationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditLocationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://location.service/", name = "editLocationResponse")
    public JAXBElement<EditLocationResponse> createEditLocationResponse(EditLocationResponse value) {
        return new JAXBElement<EditLocationResponse>(_EditLocationResponse_QNAME, EditLocationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteLocationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://location.service/", name = "deleteLocationResponse")
    public JAXBElement<DeleteLocationResponse> createDeleteLocationResponse(DeleteLocationResponse value) {
        return new JAXBElement<DeleteLocationResponse>(_DeleteLocationResponse_QNAME, DeleteLocationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteLocation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://location.service/", name = "deleteLocation")
    public JAXBElement<DeleteLocation> createDeleteLocation(DeleteLocation value) {
        return new JAXBElement<DeleteLocation>(_DeleteLocation_QNAME, DeleteLocation.class, null, value);
    }

}
