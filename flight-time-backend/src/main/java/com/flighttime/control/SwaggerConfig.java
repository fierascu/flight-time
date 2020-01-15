package com.flighttime.control;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration // Add spring configuration behaviour to the config class
@EnableSwagger2 // Enabling Swagger Version 2
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(generateAPIInfo())
        .select()
        //Here adding base package to scan controllers. This will scan only controllers inside
        //specific package and include in the swagger documentation
        .apis(RequestHandlerSelectors.basePackage("com.apiapp.springbootproject.controller"))
        .paths(PathSelectors.any())
        .build();
  }

  //Api information
  private ApiInfo generateAPIInfo() {
    return new ApiInfo("Flight Time App", "Implementing Swagger with SpringBoot Application for Flight Time App",
        "1.0.0",
        "", getContacts(), "", "", new ArrayList());
  }

  // Developer Contacts
  private Contact getContacts() {
    return new Contact("Geo", "", "me@me.com");
  }

}