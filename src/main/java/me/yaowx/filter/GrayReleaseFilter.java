package me.yaowx.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import lombok.extern.slf4j.Slf4j;
import me.yaowx.entity.GrayReleaseConfig;
import me.yaowx.resources.GrayReleaseManager;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Random;

/**
 * @author: Yaowx
 * @email worthyyao@qq.com
 * @Date: 2021/12/6
 * 灰度发布filter
 */
@Configuration
@Slf4j
public class GrayReleaseFilter extends ZuulFilter {

    @Resource
    private GrayReleaseManager grayReleaseManager;

    @Override
    public String filterType() {
        return null;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //基于配置表和path匹配，判断这个service是否启用了灰度发布功能
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String requestUri = request.getRequestURI();
        Map<String, GrayReleaseConfig> grayReleaseConfigMap = grayReleaseManager.grayReleaseConfigs;
        for (String path : grayReleaseConfigMap.keySet()) {
            if (requestUri.contains(path)) {
                GrayReleaseConfig grayReleaseConfig = grayReleaseConfigMap.get(path);
                if (grayReleaseConfig.getEnableGrayRelease() == 1) {
                    log.info("该服务service启动了灰度发布的功能");
                    return true;
                }
            }
        }
        log.info("不启动灰度发布功能");
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        Random random = new Random();
        //生成0-100之间的一个随机数
        int seed = random.nextInt(100);
        //只有当随机数到了50的时候，才会调用新版本路由
        if (seed == 50) {
            // 该方法是继承自一个开源包，这个包继承了ribbon的功能，可以通过这个功能，进行自动的转发到不同的service里面
            //具体使用可以参照https://github.com/jmnarloch/ribbon-discovery-filter-spring-cloud-starter，发现国内很多都是基于这个包去实现的
            RibbonFilterContextHolder.getCurrentContext()
                    .add("version", "new");
        } else {
            RibbonFilterContextHolder.getCurrentContext()
                    .add("version", "old");
        }

        return null;
    }
}
