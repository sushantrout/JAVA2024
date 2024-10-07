package com.tech.service.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "task-service")
public interface TaskClient {
        @GetMapping(value = "/task")
        String getTask();
}
