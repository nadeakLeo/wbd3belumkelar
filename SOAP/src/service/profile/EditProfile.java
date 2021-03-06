package service.profile;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface EditProfile {
  @WebMethod
  String editProfile(String token, String username, String userid, String name, String phone, boolean isDriver, String picture);

}
