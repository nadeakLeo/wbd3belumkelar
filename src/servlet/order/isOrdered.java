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
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Map;

@WebServlet(name = "isOrdered")
public class isOrdered extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
//        out.println("Masukk");
        Connection conn = null;
        try {
            String DBURL = "jdbc:mysql://localhost/pr-ojek-ojekonline?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // TODO :should read from config file later
            conn = (Connection) DriverManager.getConnection(DBURL, "root", "");

            Statement stmt = (Statement) conn.createStatement();

            Map<String, String> cookiesMap = CookiesMap.getCookiesMap(request.getCookies());
            String userId = cookiesMap.get("id");

            String query = "select isFinding, isComplete from user where user_id = " + userId;
            ResultSet res = stmt.executeQuery(query);
            int isFinding = 0;
            while(res.next()) {
                isFinding = res.getInt("isFinding");
            }

//            out.println(isFinding);
            if (isFinding == 1) {
                request.getRequestDispatcher("/findingorder.jsp").forward(request, response);
//                out.println("Still Finding");
            } else {
                //ganti ama chatbox driver
                out.println("Chatbox");
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
