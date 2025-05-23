package com.umarov.showtimeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.umarov"})
@EnableDiscoveryClient
@EnableFeignClients
public class ShowtimeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShowtimeServiceApplication.class, args);
    }

}
