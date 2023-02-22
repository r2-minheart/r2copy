package com.r2comms.rclone.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer   {
    @Value("${spring.servlet.multipart.location}")   
    private String uploadPath;
    
//   1) addResourceHandler : URL path 지정
//    - 위의 설정 대로 했을 경우 localhost8080/images 와 같이 됩니다.
//   2) addResourceLocations : 이미지가 업로드 될 실제 경로
//    - 반드시 경로의 마지막은 "/"와 같이 끝나야 합니다.
//    - 만약 images/ 로 끝나지 않고 images로 끝날 경우 정상적으로 설정이 되지 않습니다.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/upload/**")
        .addResourceLocations("file:///" + uploadPath + "/");
    }
}