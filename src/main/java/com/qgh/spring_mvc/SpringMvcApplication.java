package com.qgh.spring_mvc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
/**排除启动就启动redis*/
@SpringBootApplication(exclude={
        RedisAutoConfiguration.class,
        RedisRepositoriesAutoConfiguration.class})
@MapperScan("com.qgh.spring_mvc.moduels.dao")//自动扫描接口映射器
public class SpringMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcApplication.class, args);
    }

}
