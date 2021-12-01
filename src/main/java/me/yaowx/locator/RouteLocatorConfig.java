package me.yaowx.locator;

import me.yaowx.dao.GatewayDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Yaowx
 * @email worthyyao@qq.com
 * @Date: 2021/12/1
 */
@Configuration
public class RouteLocatorConfig {

    @Autowired
    ZuulProperties zuulProperties;
    @Autowired
    ServerProperties server;
    @Autowired
    private GatewayDao gatewayDao;

    @Bean
    public DynamicRouteLocator dynamicRouteLocator() {
        DynamicRouteLocator dynamicRouteLocator = new DynamicRouteLocator(this.server.getServlet().getContextPath(), this.zuulProperties, gatewayDao);
        return dynamicRouteLocator;
    }
}
