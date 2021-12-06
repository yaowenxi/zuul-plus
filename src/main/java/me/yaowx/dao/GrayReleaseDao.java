package me.yaowx.dao;

import me.yaowx.entity.GrayReleaseConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: Yaowx
 * @email worthyyao@qq.com
 * @Date: 2021/12/6
 */
public interface GrayReleaseDao extends JpaRepository<GrayReleaseConfig, Integer> {
}
