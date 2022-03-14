package com.aarish.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;


@SpringBootApplication
public class UserServiceApplication implements CommandLineRunner {

    @Value("classpath:h2/init.sql")
    private Resource initSQL;
    @Autowired
    private R2dbcEntityTemplate entityTemplate;

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String sql = StreamUtils.copyToString(initSQL.getInputStream(), StandardCharsets.UTF_8);
        System.out.println(sql);
        this.entityTemplate
                .getDatabaseClient()
                .sql(sql)
                .then()
                .subscribe();

    }
}
