package servlet.order;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import utility.CookiesMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.Map;

@WebServlet(name = "FindOrder")
public class FindOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection conn = null;
        try {
            String DBURL = "jdbc:mysql://localhost/pr-ojek-ojekonline?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // TODO :should read from config file later
            conn = (Connection) DriverManager.getConnection(DBURL, "root", "");

            Statement stmt = (Statement) conn.createStatement();
//            FetchProfileImplService fetchProfileImplService = new FetchProfileImplService();
//            FetchProfile fetchProfile = fetchProfileImplService.getFetchProfileImplPort();

            Map<String, String> cookiesMap = CookiesMap.getCookiesMap(request.getCookies());
//            String token = cookiesMap.get("token");
            String userId = cookiesMap.get("id");
//            String username = cookiesMap.get("uname");

            String query = "update user set isFinding = 1 where user_id = " + userId;
            stmt.execute(query);

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Map<String, String> cookiesMap = CookiesMap.getCookiesMap(request.getCookies());
//        if (!cookiesMap.containsKey("token")) {
//            response.sendRedirect("index.jsp?error=notoken");
//        } else {
//            String token = cookiesMap.get("token");
//            String userId = cookiesMap.get("id");
//            String username = cookiesMap.get("uname");
//            // fetch user profile using soap api
//            FetchProfileImplService fetchProfileImplService = new FetchProfileImplService();
//            FetchProfile fetchProfile = fetchProfileImplService.getFetchProfileImplPort();
//            PrintWriter out = response.getWriter();
//            String reply = fetchProfile.getProfile(token, username, userId);
//            List<Map<String, String>> maps = StringMapper.toMapArray(reply);
//            response.setIntHeader("Refresh", 1);
////            request.getRequestDispatcher("/findingorder.jsp").forward(request, response);
//            if (maps.get(0).get("isFinding").equals("true")) {
//                out.println("Finding Driver");
//            } else {
//                out.println("Chatbox");
//            }
//        }
//        PrintWriter out = response.getWriter();
//        out.println("Finding Order");
        request.getRequestDispatcher("/findingorder.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
