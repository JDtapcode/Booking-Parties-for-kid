package com.swp.birthdaybooking.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CloudConfig {

    @Value("${cloudinary.cloud_name}")
    private String cloudName;
    @Value("${cloudinary.api_key}")
    private String cloudApiKey;
    @Value("${cloudinary.api_secret}")
    private String cloudApiSecret;
    @Value("${cloudinary.secure}")
    private boolean cloudSecure;



    @Bean
    public Cloudinary getCloudinary() {
        return new Cloudinary(Map.of(
                "cloud_name", cloudName,
                "api_key", cloudApiKey,
                "api_secret", cloudApiSecret,
                "secure", cloudSecure
        ));
    }
}
