package com.anime.test_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author
 * @date 2019-11-14
 */
@SpringBootApplication
@EntityScan("com.anime.framework.domain.test")//扫描实体类
@ComponentScan(basePackages={"com.anime.api"})//扫描接口
@ComponentScan(basePackages={"com.anime.framework"})//扫描common
@ComponentScan(basePackages={"com.anime.test_client"})//扫描本项目下的所有类,不加也会扫描
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class,args);
    }
}
