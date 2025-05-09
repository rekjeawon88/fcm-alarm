package com.example.fcmdemo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class FilterConfig {

    // ServiceWorkerHeaderFilter 타입의 Bean을 주입받는 필드
    private final OncePerRequestFilter serviceWorkerHeaderFilter;

    // ServiceWorkerHeaderFilter 타입의 Bean을 주입받는 생성자
    public FilterConfig(OncePerRequestFilter serviceWorkerHeaderFilter) {
        this.serviceWorkerHeaderFilter = serviceWorkerHeaderFilter;
    }

    /**
     * ServiceWorkerHeaderFilter를 등록하는 Bean
     *
     * @return FilterRegistrationBean 객체
     */
    @Bean
    public FilterRegistrationBean<OncePerRequestFilter> serviceWorkerFilterRegistration() {
        // FilterRegistrationBean 객체 생성
        FilterRegistrationBean<OncePerRequestFilter> registration = new FilterRegistrationBean<>();

        // 등록할 필터 설정
        registration.setFilter(serviceWorkerHeaderFilter);
        registration.addUrlPatterns("/firebase-messaging-sw.js");
        registration.setName("serviceWorkerHeaderFilter");
        registration.setOrder(1);

        // 생성한 FilterRegistrationBean 객체 반환
        return registration;
    }
}
