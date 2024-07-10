package com.yedam.app;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class ResourceConfiguration implements WebMvcConfigurer{
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		  registry.addResourceHandler("/show/**")
	        .addResourceLocations("file:///C:/upload/")      
	        
	        // 접근 파일 캐싱 시간 
		.setCacheControl(CacheControl.maxAge(1, TimeUnit.MINUTES));
	}
}
