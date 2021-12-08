package com.yeyu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 13474
 * @Package com.yeyu.config
 * @date 2021/12/522:03
 */
@Configuration
@EnableSwagger2
public class Swagger {
    @Bean
    public Docket creatApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yeyu.controller"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(securityContexts())
                .securitySchemes(apiKeyList());
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("云E办")
                .description("接口文档")
                .contact(new Contact("q13474","http:127.0.0.1:8888/doc.html","1347490146@qq.com"))
                .version("1.0")
                .build();

    }
    private List<ApiKey> apiKeyList(){
        List<ApiKey> res = new ArrayList<>();
        ApiKey apiKey = new ApiKey("Authorization","Authorization","Header");
        res.add(apiKey);
        return res;
    }
    private List<SecurityContext> securityContexts(){
        List<SecurityContext> res = new ArrayList<>();
        res.add(getContextByPath("/hello/.*"));
        return res;
    }

    private SecurityContext getContextByPath(String path) {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(path))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> res = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global","accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        res.add(new SecurityReference(" Authorization",authorizationScopes));
        return res;
    }
}
