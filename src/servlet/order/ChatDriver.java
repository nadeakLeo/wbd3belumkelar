package servlet.order;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.DriverManager;

@WebServlet(name = "ChatDriver")
public class ChatDriver extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String driver_id = request.getParameter("driver_id");
        Cookie cookie = new Cookie("driver_id", driver_id);
        cookie.setMaxAge(60*60); //1 hour
        response.addCookie(cookie);
        String pick = request.getParameter("pick");
        String dest = request.getParameter("dest");
        Connection conn = null;
        try {
            String DBURL = "jdbc:mysql://localhost/pr-ojek-ojekonline?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // TODO :should read from config file later
            conn = (Connection) DriverManager.getConnection(DBURL, "root", "");

            Statement stmt = (Statement) conn.createStatement();
//            FetchProfileImplService fetchProfileImplService = new FetchProfileImplService();
//            FetchProfile fetchProfile = fetchProfileImplService.getFetchProfileImplPort();

            String query = "update user set isFinding = 0 where user_id = " + driver_id;
            stmt.execute(query);

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("ordered_driver", driver_id);
        session.setAttribute("ordered_pick", pick);
        session.setAttribute("ordered_dest", dest);
//        request.getRequestDispatcher("/chatojekuser.jsp").forward(request, response);

        response.sendRedirect("http://localhost:3000/users");
    }
}
