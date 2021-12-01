package me.yaowx.dao;

import me.yaowx.entity.GatewayApiRoute;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: Yaowx
 * @email worthyyao@qq.com
 * @Date: 2021/12/1
 */
public interface GatewayDao extends JpaRepository<GatewayApiRoute, String> {
}
