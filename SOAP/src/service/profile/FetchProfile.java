package service.profile;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface FetchProfile {
  @WebMethod
  String getProfile(String token, String username, String userId);
  @WebMethod
  String getLocation(String token, String username, String userId);

}
