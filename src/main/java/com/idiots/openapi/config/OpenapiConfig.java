package com.idiots.openapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class OpenapiConfig {

    @Value("${openapi.baseUrl}")
    private String baseUrl;

    /**
     * HTTP 요청을 할 때 service key를 queryParam으로 전달
     * WebClient가 queryParam을 UriComponentsBuilder.encode() 방식을 이용해서 인코딩 -> service key의 값이 달라짐
     * 이를 해결하기 위해 DefaultUriBuilderFactory를 만들고 encoding모드를 지정 후 WebClient객체에 넣어줌
     */
    public DefaultUriBuilderFactory builderFactory(){
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(baseUrl);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        return factory;
    }

    @Bean
    public WebClient webClient(){
        return WebClient.builder()
                .uriBuilderFactory(builderFactory())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(baseUrl)
                .build();
    }

}
