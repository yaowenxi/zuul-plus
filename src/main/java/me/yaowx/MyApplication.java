package me.yaowx;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Yaowx
 * @email worthyyao@qq.com
 * @Date: 2021/11/29
 */
@RestController
@SpringBootApplication
@EnableZuulProxy
public class MyApplication {


    @GetMapping
    String home(){
        return "hello world";
    }

    @GetMapping("dev")
    String dev(){
        return "dev tool";
    }


    public static void main(String[] args) {
        SpringApplication application=new SpringApplication(MyApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
