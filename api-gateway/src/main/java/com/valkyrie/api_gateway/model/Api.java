package com.valkyrie.api_gateway.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration
public class Api {
    private RouterFunction<ServerResponse> routeGet(String id, String pattern, String uri) {
        return route(id).GET(pattern,http()).before(uri(uri)).build();
    }

    private RouterFunction<ServerResponse> routePost(String id, String pattern, String uri) {
        return route(id).POST(pattern,http()).before(uri(uri)).build();
    }

    private RouterFunction<ServerResponse> routeDelete(String id, String pattern, String uri) {
        return route(id).DELETE(pattern,http()).before(uri(uri)).build();
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {

        return routeGet("job-service-get", "/job/**", "http://localhost:8081/")
                .and(routePost("job-service-post", "/job/**", "http://localhost:8081/"))
                .and(routeDelete("job-service-delete", "/job/**", "http://localhost:8081/"))
                .and(routeGet("employee-service-get", "/employee/**", "http://localhost:8082/"))
                .and(routePost("employee-service-Post", "/employee/**", "http://localhost:8082/"))
                .and(routeDelete("employee-service-delete", "/employee/**", "http://localhost:8082/"))
                .and(routePost("user", "/user/**", "http://localhost:8083/"));
    }
}
