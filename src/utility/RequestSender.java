package utility;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

public class RequestSender {
    public static String sendRequest(String url, String method, String contentType, String data) throws UnknownHostException {
        //Get IP
        InetAddress inet = InetAddress.getLocalHost();
        String ip = inet.getHostAddress();

        data = data + "&ip=" + ip;

        HttpURLConnection httpURLConnection;
        StringBuilder result = new StringBuilder();
        try {
            URL urlSend = new URL(url);
            httpURLConnection = (HttpURLConnection)urlSend.openConnection();
            httpURLConnection.setRequestMethod(method);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Type", contentType);

            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.writeBytes(data);
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpURLConnection.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.append("FAILED");
        }
        return result.toString();
    }
}

