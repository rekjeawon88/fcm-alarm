package com.example.fcmdemo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class FilterConfig {

    private final OncePerRequestFilter serviceWorkerHeaderFilter;

    public FilterConfig(OncePerRequestFilter serviceWorkerHeaderFilter) {
        this.serviceWorkerHeaderFilter = serviceWorkerHeaderFilter;
    }

    @Bean
    public FilterRegistrationBean<OncePerRequestFilter> serviceWorkerFilterRegistration() {
        FilterRegistrationBean<OncePerRequestFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(serviceWorkerHeaderFilter);
        registration.addUrlPatterns("/firebase-messaging-sw.js");
        registration.setName("serviceWorkerHeaderFilter");
        registration.setOrder(1);
        return registration;
    }
}
