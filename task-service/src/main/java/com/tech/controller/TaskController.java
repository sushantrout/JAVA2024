package com.tech.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/task")
@Slf4j
public class TaskController {

    @GetMapping
    public String getTask() {
        log.info("Returning task");
        return "Sushant Kumar rout";
    }
}
