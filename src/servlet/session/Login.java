package servlet.session;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import utility.RequestSender;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.util.Map;

@WebServlet(name = "Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        // get the username and password from POST method
        String username = request.getParameter("uname");
        String password = request.getParameter("pass");
        String ip = request.getRemoteAddr();
        if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
            InetAddress inet = InetAddress.getLocalHost();
            ip = inet.getHostAddress();
        }

        String userAgent = request.getHeader("User-Agent");

        String data = String.format("uname=%s&pass=%s&ua=%s&ip=%s", username, password, userAgent, ip);

        String reply = RequestSender.sendRequest("http://localhost:8084/loginservice",
                "POST", "application/x-www-form-urlencoded", data);

        if (reply != "") {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, String>>() {
            }.getType();
            Map<String, String> map = gson.fromJson(reply, type);

            if (map.containsKey("token") && map.containsKey("id")) {
                out.print("token:" + map.get("token"));
                out.print("id:" + map.get("id"));
                Cookie cookie;
                cookie = new Cookie("token", map.get("token"));
                cookie.setMaxAge(60*60); //1 hours
                response.addCookie(cookie);

                cookie = new Cookie("id", map.get("id"));
                cookie.setMaxAge(60*60); //1 hour
                response.addCookie(cookie);

                cookie = new Cookie("uname", username);
                cookie.setMaxAge(60*60); //1 hour
                response.addCookie(cookie);

                Cookie[] cookies = request.getCookies();

                if (cookies != null) {
                    for (Cookie cookiea : cookies) {
                        out.println(cookiea.getName());
                        out.println(cookiea.getValue());
                    }
                }

                response.sendRedirect("http://localhost:8085/index.jsp");
            } else {
                // login failed
                response.sendRedirect("http://localhost:8085/index.jsp?error=loginfailed");
            }
        } else {
            // login failed
            response.sendRedirect("http://localhost:8085/index.jsp?error=loginfailed");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
