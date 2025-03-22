package com.nagp.amcart.security.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.Authentication;

import java.util.UUID;

import static com.nagp.amcart.security.constant.SecurityConstant.USERNAME;

@UtilityClass
public class SecurityUtil {
    public static String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt.getClaimAsString(USERNAME);
        }

        return null;
    }

    public static String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt.getClaimAsString(USERNAME);
        }

        return null;
    }

    public static UUID stringToUUID(String uuidString) {
        return UUID.fromString(uuidString);
    }
}
