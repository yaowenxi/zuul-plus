package me.yaowx.listener;

import me.yaowx.locator.DynamicRouteLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * @author: Yaowx
 * @email worthyyao@qq.com
 * @Date: 2021/12/1
 * <p>
 * Spring的监听机制
 */
@Service
public class RefreshRouteService implements ApplicationListener<ApplicationEvent> {

    @Autowired
    private DynamicRouteLocator dynamicRouteLocator;

    @Autowired
    private ApplicationEventPublisher publishEvent;

    @Autowired
    private ZuulHandlerMapping zuulHandlerMapping;

    /**
     * 动态路由实现 调用refreshRoute() 发布刷新路由事件
     */
    public void refreshRoute() {
        RoutesRefreshedEvent routesRefreshedEvent = new RoutesRefreshedEvent(dynamicRouteLocator);
        publishEvent.publishEvent(routesRefreshedEvent);
    }


    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        zuulHandlerMapping.setDirty(true);

    }
}
