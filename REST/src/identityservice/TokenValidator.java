package identityservice;

import org.w3c.dom.Document;
import utility.DatabaseConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TokenValidator extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/xml");

        try {
            InputStream inputStream = request.getInputStream();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document soap = documentBuilder.parse(inputStream);

            String token = soap.getElementsByTagName("token").item(0).getTextContent();

            String uname = soap.getElementsByTagName("uname").item(0).getTextContent();


            Connection dbconnect = DatabaseConnector.connect("pr-ojek-identity");
            String selectQuery = " SELECT (if(expiry_time > now(), 'valid', 'expired')) as status from user WHERE uname = ? AND access_token = ?";
            PreparedStatement preparedStatement = dbconnect.prepareStatement(selectQuery);
            preparedStatement.setString(1, uname);
            preparedStatement.setString(2, token);
            ResultSet queryResult = preparedStatement.executeQuery();

            String status = "invalid";
            if (queryResult.next()){
                status = queryResult.getString("status");
            }

            MessageFactory factory = MessageFactory.newInstance();
            SOAPMessage soapMsg = factory.createMessage();
            SOAPPart part = soapMsg.getSOAPPart();

            SOAPEnvelope envelope = part.getEnvelope();
            SOAPHeader header = envelope.getHeader();
            SOAPBody body = envelope.getBody();

            SOAPBodyElement element = body.addBodyElement(envelope.createName("status"));
            element.addTextNode(status);

            soapMsg.writeTo(response.getOutputStream());



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
