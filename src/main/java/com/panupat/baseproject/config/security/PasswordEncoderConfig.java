package com.panupat.baseproject.config.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.SecureRandom;

@Getter
@Setter
@Component
@ConfigurationProperties("bcrypt")
public class PasswordEncoderConfig {

    /**
     * The version of bcrypt, can be 2a,2b,2y
     */
    private BCryptPasswordEncoder.BCryptVersion version = BCryptPasswordEncoder.BCryptVersion.$2A;
    /**
     * Strength the log rounds to use, between 4 and 31
     */
    private int strength = -1;
    /**
     * The secure random string
     */
    private String random;

    @Bean
    public PasswordEncoder passwordEncoder() {
        SecureRandom secureRandom = null;
        if (StringUtils.hasText(random))
            secureRandom = new SecureRandom(random.getBytes());
        return new BCryptPasswordEncoder(version, strength, secureRandom);
    }
}
