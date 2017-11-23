package service.order;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Order3 {
  @WebMethod
  String getDriverData_Account (String token, String username, String driver_id);
  String getDriverData_Service (String token, String username, String driver_id);
}
