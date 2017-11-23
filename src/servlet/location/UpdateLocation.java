package servlet.location;

import service.location.Location;
import service.location.LocationImplService;
import utility.CookiesMap;
import utility.RequestSender;
import utility.StringMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class UpdateLocation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> cookiesMap = CookiesMap.getCookiesMap(request.getCookies());

        if (!cookiesMap.containsKey("token")) {
            response.sendRedirect("index.jsp?error=notoken");
        } else {
            String token = cookiesMap.get("token");
            String username = cookiesMap.get("uname");
            String userId = cookiesMap.get("id");
            String newLocation = request.getParameter("newlocation");
            String oldLocation = request.getParameter("oldlocation");
            // update edit location using soap api
            LocationImplService locationImplService = new LocationImplService();
            Location location = (Location) locationImplService.getLocationImplPort();

            String reply = location.editLocation(token, username, userId, newLocation, oldLocation);
            List<Map<String, String>> maps = StringMapper.toMapArray(reply);

            if (maps.get(0).get("status").equals("expired")) {
                // renew token here
                String data = String.format("token=%s", token);
                String restRequest = RequestSender.sendRequest("http://localhost:8084/renewtoken", "POST", "application/x-www-form-urlencoded", data);
                // need to send update location again
                reply = location.editLocation(token, username, userId, newLocation, oldLocation);
                maps = StringMapper.toMapArray(reply);
            }

            if (maps.get(0).get("status").equals("valid"))
                response.sendRedirect("/editlocation");
            else
                response.sendRedirect("/index.jsp?error=token" + maps.get(0).get("status"));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
