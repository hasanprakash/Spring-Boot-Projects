package com.security.vault;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("mysql")
public class Credentials {
    private String user;
    private String password;

    
}
