package me.yaowx.resources;

import me.yaowx.dao.GrayReleaseDao;
import me.yaowx.entity.GrayReleaseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Yaowx
 * @email worthyyao@qq.com
 * @Date: 2021/12/6
 * <p>
 * 灰度发布定时任务，用于定时从数据库loading灰度配置到内存中
 */
@Component
public class GrayReleaseManager {

    public Map<String, GrayReleaseConfig> grayReleaseConfigs = new ConcurrentHashMap<String, GrayReleaseConfig>();

    @Autowired
    private GrayReleaseDao grayReleaseDao;


    @Scheduled(fixedRate = 5000)
    private void refreshRoute() {
        System.out.println("刷新gray配置");
        List<GrayReleaseConfig> grayReleaseConfigList = grayReleaseDao.findAll();
        for (GrayReleaseConfig grayReleaseConfig : grayReleaseConfigList) {
            grayReleaseConfigs.put(grayReleaseConfig.getPath(), grayReleaseConfig);
        }
    }

}
