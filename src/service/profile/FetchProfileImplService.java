
package service.profile;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "FetchProfileImplService", targetNamespace = "http://profile.service/", wsdlLocation = "http://localhost:8099/services/profile/fetchprofile?wsdl")
public class FetchProfileImplService
    extends Service
{

    private final static URL FETCHPROFILEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException FETCHPROFILEIMPLSERVICE_EXCEPTION;
    private final static QName FETCHPROFILEIMPLSERVICE_QNAME = new QName("http://profile.service/", "FetchProfileImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8099/services/profile/fetchprofile?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        FETCHPROFILEIMPLSERVICE_WSDL_LOCATION = url;
        FETCHPROFILEIMPLSERVICE_EXCEPTION = e;
    }

    public FetchProfileImplService() {
        super(__getWsdlLocation(), FETCHPROFILEIMPLSERVICE_QNAME);
    }

    public FetchProfileImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), FETCHPROFILEIMPLSERVICE_QNAME, features);
    }

    public FetchProfileImplService(URL wsdlLocation) {
        super(wsdlLocation, FETCHPROFILEIMPLSERVICE_QNAME);
    }

    public FetchProfileImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, FETCHPROFILEIMPLSERVICE_QNAME, features);
    }

    public FetchProfileImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public FetchProfileImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns FetchProfile
     */
    @WebEndpoint(name = "FetchProfileImplPort")
    public FetchProfile getFetchProfileImplPort() {
        return super.getPort(new QName("http://profile.service/", "FetchProfileImplPort"), FetchProfile.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns FetchProfile
     */
    @WebEndpoint(name = "FetchProfileImplPort")
    public FetchProfile getFetchProfileImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://profile.service/", "FetchProfileImplPort"), FetchProfile.class, features);
    }

    private static URL __getWsdlLocation() {
        if (FETCHPROFILEIMPLSERVICE_EXCEPTION!= null) {
            throw FETCHPROFILEIMPLSERVICE_EXCEPTION;
        }
        return FETCHPROFILEIMPLSERVICE_WSDL_LOCATION;
    }

}
