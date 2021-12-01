主体的思路如下：

1、基于数据库，实现一个动态路由表

2、基于Spring 的事件机制，以及Zuul的SimpleRouteLocator、RefreshableRouteLocator功能，实现当触发刷新时，从数据库loading相关网关路由，并覆盖之前的路由

3、在接口中实现调用接口，当接口触发时，可以调用Spring的EventPublish机制，实现路由刷新

4、访问`http://localhost:8080/actuator/routes`验证结果是否正确

第二步的核心代码如下：
```java
package me.yaowx.locator;

import lombok.extern.slf4j.Slf4j;
import me.yaowx.dao.GatewayDao;
import me.yaowx.entity.GatewayApiRoute;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Yaowx
 * @email worthyyao@qq.com
 * @Date: 2021/12/1
 */
@Slf4j
public class DynamicRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {
    private final GatewayDao gatewayDao;

    public DynamicRouteLocator(String servletPath, ZuulProperties properties, GatewayDao gatewayDao) {
        super(servletPath, properties);
        this.gatewayDao = gatewayDao;
    }

    @Override
    public void refresh() {
        doRefresh();
    }

    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<String, ZuulProperties.ZuulRoute>();
        //从application.properties中加载路由信息
        routesMap.putAll(super.locateRoutes());
        LinkedHashMap<String, ZuulProperties.ZuulRoute> zuulRoutes = getDbRoutes();
        //从数据库中查出来的route放在后面，这样可以覆盖之前的route
        routesMap.putAll(zuulRoutes);

        return routesMap;
    }

    //从数据库中加载配置的路由表，并赋值给到ZuulRoute核心类，供后续加载使用
    private LinkedHashMap<String, ZuulProperties.ZuulRoute> getDbRoutes() {
        List<GatewayApiRoute> gatewayApiRoutes = gatewayDao.findAll();
        LinkedHashMap<String, ZuulProperties.ZuulRoute> routes = new LinkedHashMap<>();
        for (GatewayApiRoute gatewayApiRoute : gatewayApiRoutes) {
            ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
            BeanUtils.copyProperties(gatewayApiRoute, zuulRoute);
            routes.put(zuulRoute.getPath(), zuulRoute);

        }
        return routes;
    }
}
```