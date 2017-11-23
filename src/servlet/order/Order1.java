package servlet.order;

import utility.CookieChecker;
import utility.CookiesMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "Order1")
public class Order1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> cookiesMap = CookiesMap.getCookiesMap(request.getCookies());
        if (!cookiesMap.containsKey("token")) {
            response.sendRedirect("index.jsp?error=notoken");
        } else {
            String token = cookiesMap.get("token");
            String userId = cookiesMap.get("id");
            String username = cookiesMap.get("uname");

            if (CookieChecker.check(request.getCookies())) {
                request.getRequestDispatcher("/order.jsp").forward(request, response);
            } else {
                response.sendRedirect("index.jsp?error=tokenerror");
            }
        }

    }
}
