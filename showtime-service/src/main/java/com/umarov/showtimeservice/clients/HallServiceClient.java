package com.umarov.showtimeservice.clients;

import com.umarov.showtimeservice.dto.HallResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hall-service")
public interface HallServiceClient {

    @GetMapping("/api/hall/{id}")
    HallResponse getHallById(@PathVariable("id") Long id);
}
