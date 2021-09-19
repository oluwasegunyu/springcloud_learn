package com.yxy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
//@EnableFeignClients(basePackages = {"com.yxy.springcloud"})
public class DeptConsumer_feign_8081 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer_feign_8081.class, args);
    }
}
