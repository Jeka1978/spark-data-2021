package com.epam.jpoint.sparkdata2021;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class SparkData2021Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SparkData2021Application.class, args);

        CriminalRepo criminalRepo = context.getBean(CriminalRepo.class);
        List<Criminal> criminals = criminalRepo.findByNameContains("ova");
        criminals.get(0).getOrders().forEach(System.out::println);
    }

}
