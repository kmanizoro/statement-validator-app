package com.bank.robo.customer.statement.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bank.robo.customer.statement.util.AppConstants;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Which is used to configure the swagger
 * 
 * @author mani.kasi
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public SwaggerConfig() {
		// default constructor
	}

	@Value("${created-by}")
	private String createdBy;

	@Value("${app-title}")
	private String appTitle;

	@Value("${app-desc}")
	private String appDesc;

	@Value("${app-version}")
	private String appVersion;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(AppConstants.APP_BASE_PACKAGE))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	public ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(appTitle).description(appDesc).version(appVersion)
				.termsOfServiceUrl(createdBy).build();
	}
}
