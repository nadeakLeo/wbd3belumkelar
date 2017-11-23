package identityservice;

import com.google.gson.JsonObject;
import utility.DatabaseConnector;
import utility.TokenGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginService extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String username = request.getParameter("uname");
        String password = request.getParameter("pass");

        // create connection to the database
        Connection databaseConnection = DatabaseConnector.connect("pr-ojek-identity");

        try {
            String selectQuery = "select * from user where uname = ? and password = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet result = preparedStatement.executeQuery();

            // TODO :make this one clean and better
//            String requestHost = request.getHeader("Referer").substring(0, request.
//                    getHeader("Referer").indexOf('/', 10));
//            String responseAddress = requestHost + "/login";
//            response.sendRedirect("localhost:8085/order.jsp");

            PrintWriter out = response.getWriter();

            if (result.next()) {
                // TODO :add token
                String expiryTime = new TokenGenerator().generateExpiryTime();
                String userId = result.getString("user_id");
                String token = new TokenGenerator().generateToken(username, expiryTime);

                // insert token and expiry time to database
                String insertQuery = "update user set access_token = ?, expiry_time = ? where user_id = ?";
                PreparedStatement preparedStatement1 = databaseConnection.prepareStatement(insertQuery);
                preparedStatement1.setString(1, token);
                preparedStatement1.setString(2, expiryTime);
                preparedStatement1.setString(3, userId);

                preparedStatement1.executeUpdate();

                // send the token and user id in json format
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("token", token);
                jsonObject.addProperty("id", userId);
                out.write(jsonObject.toString());
            } else {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("error", "loginfailed");
                out.write(jsonObject.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseConnector.close(databaseConnection);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
