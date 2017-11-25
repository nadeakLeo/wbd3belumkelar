package servlet.session;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import service.register.RegisterUserImplService;
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

@WebServlet(name = "Register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        // get the POST response parameter
        String name = request.getParameter("name");
        String username = request.getParameter("uname");
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String phone = request.getParameter("phnum");
        boolean isDriver = request.getParameter("isdriver") != null;
        String is_driver ;
        if (isDriver) {
            is_driver = "1";
        } else {
            is_driver = "0";
        }

        String ip = request.getRemoteAddr();
        if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
            InetAddress inet = InetAddress.getLocalHost();
            ip = inet.getHostAddress();
        }

        String userAgent = request.getHeader("User-Agent");

        String data = String.format("name=%s&uname=%s&email=%s&pass=%s&phnum=%s&isdriver=%s&ua=%s&ip=%s",
                name, username, email, password, phone, isDriver ? "true" : "false", userAgent, ip);
        out.println(data);

        String reply = RequestSender.sendRequest("http://localhost:8084/registerservice", "POST", "application/x-www-form-urlencoded", data);
        out.println(reply);

        RegisterUserImplService reg1 = new RegisterUserImplService();
        service.register.RegisterUser reg = reg1.getRegisterUserImplPort();

        boolean register_user = reg.insertToUser(name, email, phone, "", is_driver);

        if (!reply.equals("")) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, String>>() {
            }.getType();
            Map<String, String> map = gson.fromJson(reply, type);

            if (map.containsKey("token") && map.containsKey("id") && map.containsKey("is_driver")) {
                Cookie cookie;
                cookie = new Cookie("token", map.get("token"));
                cookie.setMaxAge(60*60); //1 hour
                response.addCookie(cookie);

                cookie = new Cookie("id", map.get("id"));
                cookie.setMaxAge(60*60); //1 hour
                response.addCookie(cookie);

                cookie = new Cookie("uname", username);
                cookie.setMaxAge(60*60); //1 hour
                response.addCookie(cookie);
                out.print(map.get("is_driver"));
                if (map.get("is_driver").equals("true"))
                    response.sendRedirect("/profile");
                else
                    response.sendRedirect("/order1");
            }
        } else {
            // register failed
            response.sendRedirect("/signup");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
