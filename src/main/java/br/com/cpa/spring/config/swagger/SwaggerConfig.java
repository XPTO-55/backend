package br.com.cpa.spring.config.swagger;

import jdk.javadoc.doclet.Doclet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class SwaggerConfig {
    public static final String AUTHORIZATION_HEADER = "Authorization";

//    private ApiInfo apiInfo() {
//        return new ApiInfo("MyApp Rest APIs",
//                "APIs for MyApp.",
//                "1.0",
//                "Terms of service",
//                new Contact("test", "www.org.com", "test@emaildomain.com"),
//                "License of API",
//                "API license URL",
//                Collections.emptyList());
//    }
//
//    @Bean
//    public Doclet api() {
//        return new Docket(DocumentationType.OAS_30)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
}
