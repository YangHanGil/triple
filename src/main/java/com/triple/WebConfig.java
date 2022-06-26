package com.triple;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;


@Configuration
public class WebConfig implements WebMvcConfigurer {

//    private final AuthIntercep
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//경로
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("*")
                .exposedHeaders("Set-Cookie")
                .allowedOrigins("");//상대방주소
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploadfiles/**")
                .addResourceLocations("file:uploadfiles/")
                .setCachePeriod(20);
    }
}