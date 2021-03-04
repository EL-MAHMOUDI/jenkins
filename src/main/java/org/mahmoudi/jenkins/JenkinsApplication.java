package org.mahmoudi.jenkins;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JenkinsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JenkinsApplication.class, args);
    }

    @Bean
    CommandLineRunner loader(){
        return args -> System.out.println("Hello AMIN!");
    }
}
