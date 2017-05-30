package com.wanari.cbexample.util.sync_gateway;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Component
class SyncGatewayRestTemplate {

    private RestTemplate restTemplate;

    @PostConstruct
    private void init() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setBufferRequestBody(false);
        restTemplate = new RestTemplate(factory);
    }

    <R, B> ResponseEntity<R> post(String url, B body, HttpHeaders headers, Class<R> clazz) {
        return exchange(url, HttpMethod.POST, body, headers, clazz);
    }

    <R, B> ResponseEntity<R> put(String url, B body, HttpHeaders headers, Class<R> clazz) {
        return exchange(url, HttpMethod.PUT, body, headers, clazz);
    }

    private <R, B> ResponseEntity<R> exchange(String url, HttpMethod method, B body, HttpHeaders headers, Class<R> clazz) {
        HttpEntity httpEntity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(url, method, httpEntity, clazz);
    }

}
