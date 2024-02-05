package com.intellicreation.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * http://localhost:7777/swagger-ui.html
 * @author za
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.intellicreation.api.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("zar", "暂无", "zarkjk@163.com");
        return new ApiInfoBuilder()
                .title("基于Spring Boot的智能创作系统接口文档")
                .description("基于Spring Boot的智能创作系统接口文档")
                // 联系方式
                .contact(contact)
                // 版本
                .version("1.1.1")
                .build();
    }
}
