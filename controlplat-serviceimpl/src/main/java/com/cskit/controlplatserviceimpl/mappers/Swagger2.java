package com.cskit.controlplatserviceimpl.mappers;

import com.cskit.controlplatserviceimpl.config.SwaggerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Micro
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/15 13:18
 */
@Configuration
public class Swagger2 {

    /*    @Value("${spring.swagger.show}")
        boolean show;*/
    @Autowired
    SwaggerConfig swaggerConfig;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(swaggerConfig.getShowinfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cskit.controlplatservice"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API接口文档")
                .description("主要提供&#10; 1、查看API使用说明，&#10;2、Prod前测试使用，&#10;3、前端使用帮助")
                .version("1.0")
                .termsOfServiceUrl("http://localhost:2001/")
                .build();
    }
}
