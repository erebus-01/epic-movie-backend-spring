package com.programing.gateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiEndPoints = List.of(
            "/api/admin/register",
            "/api/admin/authentication",
            "/api/admin/validate",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndPoints.stream().noneMatch(uri -> request.getURI().getPath().contains(uri));

}