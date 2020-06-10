package com.sshy.general;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.sshy.general.mapper")
@EnableTransactionManagement
public class GeneralApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeneralApplication.class, args);
    }

}
