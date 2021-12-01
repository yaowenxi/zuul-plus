package me.yaowx.controller;

import me.yaowx.dao.GatewayDao;
import me.yaowx.listener.RefreshRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Yaowx
 * @email worthyyao@qq.com
 * @Date: 2021/12/1
 */
@RestController
public class GatewayController {

    @Autowired
    private GatewayDao gatewayDao;

    @Autowired
    private RefreshRouteService refreshRouteService;

    @GetMapping("gateway")
    public Object gatewayList() {
        return gatewayDao.findAll();
    }

    @GetMapping("gateway/refresh")
    public Object refresh() {
        refreshRouteService.refreshRoute();
        return "ok";
    }
}
