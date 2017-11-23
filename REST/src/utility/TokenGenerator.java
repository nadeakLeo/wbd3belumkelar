package utility;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TokenGenerator {
    public String generateToken(String username, String expiryTime) {
//        SecureRandom random = new SecureRandom();
//        byte bytes[] = new byte[40];
//        random.nextBytes(bytes);
//        String token = bytes.toString();
//        return token;
        String string = "ACEFGHJKLMNPQRUVWXYabcdefhijkprstuvwx" + expiryTime;
        RandomString randomizer = new RandomString(23, new SecureRandom(), string);
        return randomizer.nextString();
    }

    public String generateExpiryTime() {
        Calendar time = Calendar.getInstance();
        time.setTime(Calendar.getInstance().getTime());
        time.add(Calendar.MINUTE, 5);
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time.getTime());
        return timeStamp;
    }
}