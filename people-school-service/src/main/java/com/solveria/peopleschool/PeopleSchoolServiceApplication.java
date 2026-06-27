package com.solveria.peopleschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;

@SpringBootApplication(
        exclude = {
            MongoAutoConfiguration.class,
            MongoDataAutoConfiguration.class,
            MongoReactiveAutoConfiguration.class,
            MongoRepositoriesAutoConfiguration.class,
            MongoReactiveRepositoriesAutoConfiguration.class
        })
public class PeopleSchoolServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PeopleSchoolServiceApplication.class, args);
    }
}
