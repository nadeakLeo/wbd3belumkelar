package endpoint;

import service.history.HistoryImpl;
import service.location.LocationImpl;
import service.order.FinishOrderImpl;
import service.order.Order2Impl;
import service.order.Order3Impl;
import service.profile.EditProfileImpl;
import service.profile.FetchProfileImpl;
import service.register.RegisterUserImpl;
import service.validator.TokenValidatorImpl;

import javax.xml.ws.Endpoint;

//Endpoint publisher
public class Publisher{

  public static void main(String[] args) {



    Endpoint.publish("http://localhost:8099/services/validatetoken", new TokenValidatorImpl());
    Endpoint.publish("http://localhost:8099/services/gethistory", new HistoryImpl());
    Endpoint.publish("http://localhost:8099/services/order2", new Order2Impl());
    Endpoint.publish("http://localhost:8099/services/order3", new Order3Impl());
    Endpoint.publish("http://localhost:8099/services/finishorder", new FinishOrderImpl());
    Endpoint.publish("http://localhost:8099/services/profile/fetchprofile", new FetchProfileImpl());
    Endpoint.publish("http://localhost:8099/services/profile/editprofile", new EditProfileImpl());
    Endpoint.publish("http://localhost:8099/services/location/location", new LocationImpl());
    Endpoint.publish("http://localhost:8099/services/register/registeruser", new RegisterUserImpl());
  }

}