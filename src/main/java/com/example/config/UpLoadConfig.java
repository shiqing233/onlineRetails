package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UpLoadConfig implements WebMvcConfigurer {
        @Value("${file.staticAccessPath}")
        private String staticAccessPath;
        @Value("${file.uploadPath}")
        private String uploadPath;

        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler(staticAccessPath).addResourceLocations("file:/" + uploadPath);
            registry.addResourceHandler("/image/goods/**").addResourceLocations("file:D:/image/goods/");
            registry.addResourceHandler("/applyPdfFile/**").addResourceLocations("file:D:/applyPdfFile/");
            registry.addResourceHandler("/image/shop/**").addResourceLocations("file:D:/image/shop/");
        }
}
