package com.sshy.general.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Component
@PropertySource("application_certificate.properties")
public class MyBean {

    @Value("${PayCashServiceImpl.Deposit.Url}")
    public String Url;

    @Value("${rate}")
    public static Integer rate;

    @PostConstruct
    public void t1(){
        System.err.println(Url);
        System.err.println(rate);
        System.err.println("MyBean");
    }
    @Bean
    public RestTemplate newRestTemplate(){
        return new RestTemplate();
    }

}
