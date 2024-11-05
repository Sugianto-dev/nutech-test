package com.sugianto.nutech.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class AppConfig {

    @Value("${nutech.jwt-iss}")
    private String jwtIss;

    @Value("${nutech.jwt-secret-key}")
    private String jwtSecretKey;

}
