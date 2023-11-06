package com.simplemealplanner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@Slf4j
public class MyApplication {

    @Value("${health-check-message: Hello}")
    private String healthCheckMessage;

    @RequestMapping("/")
    String home() {
        log.info("received new health check request");
        return healthCheckMessage;
    }

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

}
