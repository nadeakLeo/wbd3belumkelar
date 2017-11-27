package service.profile;


import org.apache.commons.codec.binary.Base64;
import service.validator.TokenValidatorImpl;
import utility.DatabaseConnector;
import javax.jws.WebService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebService(endpointInterface = "service.profile.FetchProfile")
public class FetchProfileImpl implements FetchProfile{
  @Override
  public String getProfile(String token, String username, String userId) {
    TokenValidatorImpl tokenValidator = new TokenValidatorImpl();
    String status = tokenValidator.validateToken(token, username);
    if (status.equals("valid")) {
      // token valid

      // create connection to the database
      Connection databaseConnection = DatabaseConnector.connect("pr-ojek-ojekonline");
      PreparedStatement preparedStatement;

      try {
        // Generate the SQL query.
        String selectQuery = "select * from user where user_id = ?";
        preparedStatement = databaseConnection.prepareStatement(selectQuery);
        //Making use of prepared statements here to insert bunch of data
        preparedStatement.setString(1, userId);

        ResultSet result = preparedStatement.executeQuery();

        if (result.next()) {
          // successful query execution
          String name = result.getString("name");
          String isDriver = result.getString("is_driver").equals("1") ? "true" : "false";
          String rating = result.getString("rating");
          String votes = result.getString("votes");
          String email = result.getString("email");
          String phone = result.getString("phone");
          String isFinding = result.getString("isFinding").equals("1") ? "true" : "false";
          String isComplete = result.getString("isComplete").equals("1") ? "true" : "false";
          byte[] profilePictureBlob = result.getBytes("profile_picture");
          String profilePicture = Base64.encodeBase64String(profilePictureBlob);
          String profileString = String.format("status=%s&name=%s&is_driver=%s&rating=%s&votes=%s&email=%s&phone=%s&profile_picture=%s&isFinding=%s&isComplete=%s",
                  status, name, isDriver, rating, votes, email, phone, profilePicture, isFinding, isComplete);
          return profileString;
        } else {
          // failure at query execution
          return "status=error";
        }

      }
      catch (Exception e) {
        e.printStackTrace();
      } finally {

        // Close the connection.
        try {
          DatabaseConnector.close(databaseConnection);
        } catch (Exception ec) {
          ec.printStackTrace();
        }
      }
    } else {
      // status invalid or expired
      return String.format("status=%s", status);
    }

    // undefined error
    return "status=error";
  }

  @Override
  public String getLocation(String token, String username, String userId) {
    TokenValidatorImpl tokenValidator = new TokenValidatorImpl();
    String status = tokenValidator.validateToken(token, username);
    if (status.equals("valid")) {
      // token valid

      // create connection to the database
      Connection databaseConnection = DatabaseConnector.connect("pr-ojek-ojekonline");
      PreparedStatement preparedStatement;

      try {
        // Generate the SQL query.
        String selectQuery = "select * from preferred_location where user_id = ?";
        preparedStatement = databaseConnection.prepareStatement(selectQuery);
        //Making use of prepared statements here to insert bunch of data
        preparedStatement.setString(1, userId);

        ResultSet result = preparedStatement.executeQuery();

        String locationString = String.format("status=%s&", status);
        boolean hasLocation = false;
        while (result.next()) {
          hasLocation = true;
          // successful query execution
          String location = result.getString("location");
          locationString += String.format("location=%s\n-\n", location);
        }
        if (!hasLocation)
          return locationString.substring(0, locationString.length() - 1);
        return locationString.substring(0, locationString.length() - 3);

      } catch (Exception e) {
        e.printStackTrace();
      } finally {

        // Close the connection.
        try {
          DatabaseConnector.close(databaseConnection);
        } catch (Exception ec) {
          ec.printStackTrace();
        }
      }
    } else {
      // status invalid or expired
      return String.format("status=%s", status);
    }
    // undefined error
    return "status=error";
  }
}
