package identityservice;

import com.google.gson.JsonObject;
import utility.DatabaseConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class LogoutService extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String token = request.getParameter("token");
        String userId = request.getParameter("user_id");

        PrintWriter out = response.getWriter();

        // create connection to the database
        Connection databaseConnection = DatabaseConnector.connect("pr-ojek-identity");

        try {
            String updateQuery = "update user set access_token = NULL, expiry_time = NULL where user_id = ? and access_token = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateQuery);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, token);

            preparedStatement.executeUpdate();

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("success", "true");
            out.write(jsonObject.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("success", "false");
            out.write(jsonObject.toString());
        }

        DatabaseConnector.close(databaseConnection);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
