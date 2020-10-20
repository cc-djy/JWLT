package com.cc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MqttServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqttServerApplication.class, args);
    }

}
