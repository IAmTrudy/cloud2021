package com.lxp.springcloud;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        RouteLocator path_route_lxp_1 = routes.route("path_route_lxp_1",
                r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
        return path_route_lxp_1;

    }
}
