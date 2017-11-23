package utility;

import service.validator.TokenValidator;
import service.validator.TokenValidatorImplService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import java.io.IOException;



public class CookieChecker {
    public static boolean check(Cookie[] cookies) throws ServletException, IOException {
        boolean hasToken = false;
        String tokenCooky = "";
        String usernameCooky = "";
        if (cookies != null) {
            for (Cookie cooky : cookies) {
                String cookieName = cooky.getName();
                String cookieValue = cooky.getValue();
                if (cookieName.equals("token")) {
                    hasToken = true;
                    tokenCooky = cookieValue;
                } else if (cookieName.equals("uname")) {
                    usernameCooky = cookieValue;
                }
            }
            if (hasToken) {
                // TODO :validate the token
                TokenValidatorImplService tokenValidatorImplService = new TokenValidatorImplService();
                TokenValidator tokenValidator = tokenValidatorImplService.getTokenValidatorImplPort();
                String reply = tokenValidator.validateToken(tokenCooky, usernameCooky);
                if(reply.equals("valid")){
                    return true;
                } else
                    return false;

            } else {
                // dont have token cookies
                return false;
            }
        } else {
            // dont have cookies
            return false;
        }
    }
}