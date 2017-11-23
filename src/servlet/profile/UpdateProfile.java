package servlet.profile;

import service.profile.EditProfileImplService;
import service.profile.EditProfile;
import sun.misc.BASE64Encoder;
import utility.CookiesMap;
import utility.RequestSender;
import utility.StringMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "UpdateProfile")
public class UpdateProfile extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> cookiesMap = CookiesMap.getCookiesMap(request.getCookies());

        if (!cookiesMap.containsKey("token")) {
            response.sendRedirect("index.jsp?error=notoken");
        } else {
            String token = cookiesMap.get("token");
            String username = cookiesMap.get("uname");
            String userId = cookiesMap.get("id");
            Part filePart = request.getPart("newpic");
            StringBuilder stringBuilder = new StringBuilder();
            BASE64Encoder base64Encoder = new BASE64Encoder();
            InputStream inputStream = filePart.getInputStream();
            String pictureBlob = "";
            byte[] buffer = new byte[1024];
            if (filePart.getSize() > 0) {
                try {
                    // remaining is the number of bytes to read to fill the buffer
                    int remaining = buffer.length;
                    while (true) {
                        int read = inputStream.read(buffer, buffer.length - remaining, remaining);
                        pictureBlob += base64Encoder.encode(buffer);
                        if (read >= 0) { // some bytes were read
                        } else {
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            String name = request.getParameter("newname");
            String phone = request.getParameter("phone");
            Boolean status = request.getParameter("status") != null;
            // update profile using soap api
            EditProfileImplService editProfileImplService = new EditProfileImplService();
            EditProfile editProfile1 =  editProfileImplService.getEditProfileImplPort();

            PrintWriter out = response.getWriter();
            out.println(userId);
            out.println(name);
            out.println(phone);
            out.println(status);
            String reply = editProfile1.editProfile(token, username, userId, name, phone, status, pictureBlob);
            List<Map<String, String>> maps = StringMapper.toMapArray(reply);
            if (maps.get(0).get("status").equals("expired")) {
                // renew token here
                String data = String.format("token=%s", token);
                String restRequest = RequestSender.sendRequest("http://localhost:8084/renewtoken", "POST", "application/x-www-form-urlencoded", data);
                // need to send update profile again
                reply = editProfile1.editProfile(token, username, userId, name, phone, status, pictureBlob);
                maps = StringMapper.toMapArray(reply);
            }
            if (maps.get(0).get("status").equals("valid"))
                response.sendRedirect("/profile");
            else
                response.sendRedirect("/index.jsp?error=token" + maps.get(0).get("status"));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void writeBlock(int blockNumber, byte[] buffer, int length) throws IOException {
        FileOutputStream fos = new FileOutputStream("output_" + blockNumber + ".dat");
        try {
            fos.write(buffer, 0, length);
        } finally {
            fos.close();
        }
    }
}
