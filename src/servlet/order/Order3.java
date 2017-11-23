package servlet.order;

import service.order.Order3ImplService;
import utility.CookiesMap;
import utility.RequestSender;
import utility.StringMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Order3")
public class Order3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> cookiesMap = CookiesMap.getCookiesMap(request.getCookies());
        if (!cookiesMap.containsKey("token")) {
            response.sendRedirect("index.jsp?error=notoken");
        } else {
            String pick = request.getParameter("pick").toString();
            String dest = request.getParameter("dest").toString();
            String driver_id = request.getParameter("driver_id").toString();

            String token = cookiesMap.get("token");
            String username = cookiesMap.get("uname");
            String userId = cookiesMap.get("id");

            Order3ImplService o3impl = new Order3ImplService();
            service.order.Order3 o3 = o3impl.getOrder3ImplPort();
            //list map driver data service
            String driver_data_service = o3.getDriverDataService(token, username, driver_id);
            List<Map<String, String>> maps1 = StringMapper.toMapArray(driver_data_service);

            if (maps1.get(0).get("status").equals("expired")) {
                // renew token here
                String data = String.format("token=%s", token);
                String restRequest = RequestSender.sendRequest("http://localhost:8084/renewtoken", "POST", "application/x-www-form-urlencoded", data);
                // need to fetch driver data again
                driver_data_service = o3.getDriverDataService(token, username, driver_id);
                maps1 = StringMapper.toMapArray(driver_data_service);
            }

            if (maps1.get(0).get("status").equals("valid")) {
                request.setAttribute("driver_data_service", maps1.get(0));

                //list map driver data service
                String driver_data_account = o3.getDriverDataAccount(token, username, driver_id);
                List<Map<String, String>> maps2 = StringMapper.toMapArray(driver_data_account);

                if (maps2.get(0).get("status").equals("expired")) {
                    // renew token here
                    String data = String.format("token=%s", token);
                    String restRequest = RequestSender.sendRequest("http://localhost:8084/renewtoken", "POST", "application/x-www-form-urlencoded", data);
                    // need to fetch driver data again
                    driver_data_account = o3.getDriverDataAccount(token, username, driver_id);
                    maps2 = StringMapper.toMapArray(driver_data_account);
                }

                if (maps1.get(0).get("status").equals("valid")) {
                    request.setAttribute("driver_data_account", maps2.get(0));
                    request.setAttribute("pick", pick);
                    request.setAttribute("dest", dest);
                    request.setAttribute("driver_id", driver_id);

                    request.getRequestDispatcher("/order3.jsp").forward(request, response);
                } else
                    response.sendRedirect("/index.jsp?error=token" + maps2.get(0).get("status"));
            } else
                response.sendRedirect("/index.jsp?error=token" + maps1.get(0).get("status"));
        }
    }
}
