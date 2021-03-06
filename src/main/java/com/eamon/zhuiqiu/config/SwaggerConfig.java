package com.eamon.zhuiqiu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Angel on 2020/11/29.
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {

    @Bean
    public Docket api() {
        System.out.println("----XIN----2018/7/11 下午12:58 Line:22,当前类=SwaggerConfig.api()");
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                /***
                 重要的两个方法:
                 apis():指定要生成文档的接口包基本路径
                 paths():指定针对哪些请求生成接口文档
                 参考官方资料：http://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
                 ****/
                .apis(RequestHandlerSelectors.any())
//                .apis(RequestHandlerSelectors.basePackage("com.fh.controller.api"))
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("开放接口项目接口文档")
                .description("XXX API接口文档")
                .version("1.0.0")
                .termsOfServiceUrl("")
                .license("")
                .licenseUrl("")
                .build();
    }
}
