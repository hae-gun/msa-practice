package com.example.apigatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {
    public LoggingFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
//        return (exchange, chain) -> {
//            ServerHttpRequest request = exchange.getRequest();
//            ServerHttpResponse response = exchange.getResponse();
//
//            log.info("Global Filter base Message : {}", config.getBaseMessage());
//            if(config.isPreLogger()){
//                log.info("Global Filter Start : request id -> {}", request.getId());
//            }
//            return chain.filter(exchange).then(Mono.fromRunnable(() -> { // Webflux 의 Mono 객체 사용.
//                if(config.isPostLogger()){
//                    log.info("Global Filter End : response code -> {}", response.getStatusCode());
//                }
//            }));
//        };
        GatewayFilter filter = null;
        filter = new OrderedGatewayFilter((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Logging Filter base Message : {}", config.getBaseMessage());
            if(config.isPreLogger()){
                log.info("Logging PRE Filter : request id -> {}", request.getId());
            }
            return chain.filter(exchange).then(Mono.fromRunnable(() -> { // Webflux 의 Mono 객체 사용.
                if(config.isPostLogger()){
                    log.info("Logging POST Filter : response code -> {}", response.getStatusCode());
                }
            }));
        }, Ordered.LOWEST_PRECEDENCE); // HIGHEST: 가장 최상단 (Global 보다 상단) LOWEST: 가장 내부단. lambda 로 생성하면 Default HIGHEST

        return filter;
    }
    @Data
    public static class Config{
        // Put the configuration properties
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
