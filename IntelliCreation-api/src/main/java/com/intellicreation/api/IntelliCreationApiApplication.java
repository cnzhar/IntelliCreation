package com.intellicreation.api;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author za
 */
@SpringBootApplication
@MapperScan({"com.intellicreation.member.mapper", "com.intellicreation.article.mapper"})
@ComponentScan({"com.intellicreation.member.*", "com.intellicreation.article.*", "com.intellicreation.common.*"})
public class IntelliCreationApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(IntelliCreationApiApplication.class, args);
    }
}
