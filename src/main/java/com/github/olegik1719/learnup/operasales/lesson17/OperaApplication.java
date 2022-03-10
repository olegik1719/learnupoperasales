package com.github.olegik1719.learnup.operasales.lesson17;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class OperaApplication {
    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(OperaApplication.class, args);
    }
}
