package com.umarov.hallservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.umarov"})
@EnableDiscoveryClient
@EnableFeignClients
public class HallServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HallServiceApplication.class, args);
    }

}
