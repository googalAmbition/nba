package com.xz.nba;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@ServletComponentScan
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableCaching
@MapperScan("com.xz.nba.dao")
public class NbaApplication {

    public static void main(String[] args) {
        SpringApplication.run(NbaApplication.class, args);
        System.out.println("=========   启动成功    ===========");
    }

}

