package br.com.cpa.spring.config.swagger;

import jdk.javadoc.doclet.Doclet;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SpringDocConfigProperties;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springdoc.core.SwaggerUiConfigProperties;
import org.springdoc.core.providers.SpringWebProvider;
import org.springdoc.webmvc.ui.SwaggerWelcomeWebMvc;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
    public static final String AUTHORIZATION_HEADER = "Authorization";

    // @Bean
    // @ConditionalOnMissingBean
    // @ConditionalOnProperty(name = { "springdoc.use-management-port" },
    // havingValue = "false", matchIfMissing = true)
    // SwaggerWelcomeWebMvc swaggerWelcome(SwaggerUiConfigProperties
    // swaggerUiConfig,
    // SpringDocConfigProperties springDocConfigProperties,
    // SwaggerUiConfigParameters swaggerUiConfigParameters,
    // SpringWebProvider springWebProvider) {
    // return new SwaggerWelcomeWebMvc(swaggerUiConfig, springDocConfigProperties,
    // swaggerUiConfigParameters,
    // springWebProvider);
    // }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("CPA REST API")
                        .description("CPA Rest api")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
        // .externalDocs(new ExternalDocumentation()
        // .description("SpringShop Wiki Documentation")
        // .url("https://springshop.wiki.github.org/docs"));
    }

    // @Bean
    // public GroupedOpenApi publicApi() {
    // return GroupedOpenApi.builder()
    // .group("springshop-public")
    // .pathsToMatch("/public/**")
    // .build();
    // }
    // @Bean
    // public GroupedOpenApi adminApi() {
    // return GroupedOpenApi.builder()
    // .group("springshop-admin")
    // .pathsToMatch("/admin/**")
    // .addMethodFilter(method -> method.isAnnotationPresent(Admin.class))
    // .build();
    // }
}
