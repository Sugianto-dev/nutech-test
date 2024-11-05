package com.sugianto.nutech.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static String getPayloadToken() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return principal.toString();
    }

}
