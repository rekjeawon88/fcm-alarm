package com.example.fcmdemo.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 정적 리소스 핸들러 구성
     *
     * @param registry ResourceHandlerRegistry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // "/firebase-messaging-sw.js" 요청에 대한 리소스 핸들러 등록
        registry.addResourceHandler("/firebase-messaging-sw.js")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(0);  // 캐싱 비활성화
    }

    /**
     * CORS 정책 구성
     *
     * @param registry CorsRegistry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 모든 요청에 대해 CORS 정책 적용
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("Service-Worker-Allowed");
    }

    /**
     * Service Worker 헤더 추가 필터 Bean 생성
     *
     * @return OncePerRequestFilter 타입의 Bean
     */
    @Bean
    public OncePerRequestFilter serviceWorkerHeaderFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain filterChain)
                    throws ServletException, IOException {

                // "/firebase-messaging-sw.js" 요청에 대해서만 헤더 추가
                if (request.getRequestURI().contains("firebase-messaging-sw.js")) {
                    response.setHeader("Service-Worker-Allowed", "/");
                }

                // 필터 체인 계속 진행
                filterChain.doFilter(request, response);
            }
        };
    }
}
