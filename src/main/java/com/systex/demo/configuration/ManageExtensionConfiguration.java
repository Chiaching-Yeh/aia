package com.systex.demo.configuration;


import com.systex.demo.support.FunctionLogSupport;
import com.systex.demo.support.Snowflake;
import com.systex.demo.support.pebble.PebbleExtension;
import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.extension.Extension;
import io.pebbletemplates.pebble.loader.ClasspathLoader;
import io.pebbletemplates.spring.servlet.PebbleViewResolver;
import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Component
@Configuration
public class ManageExtensionConfiguration implements WebMvcConfigurer {

    @Value("${app.pebble.path}")
    private String pebblePath;
    
    @Bean
    public PebbleEngine pebbleEngine(ServletContext servletContext) {
        return new PebbleEngine.Builder().loader(new ClasspathLoader()).extension(pebbleExtension()).build();
    }

    @Bean
    public PebbleViewResolver viewResolver(ServletContext servletContext) {

    	FunctionLogSupport.start("ManageExtensionConfiguration.viewResolver");

        PebbleViewResolver viewResolver = new PebbleViewResolver(pebbleEngine(servletContext));

        viewResolver.setPrefix(pebblePath + "templates/");
        viewResolver.setSuffix(".peb,.html");
        viewResolver.setServletContext(servletContext);

        FunctionLogSupport.end("ManageExtensionConfiguration.viewResolver");

        return viewResolver;
    }

    @Bean
    public Extension pebbleExtension() {
        return new PebbleExtension();
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	
//    	FunctionLogSupport.start("ManageExtensionConfiguration.addResourceHandlers");
    	
        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/classes/static/");
        
        registry.addResourceHandler("/favicon.ico").addResourceLocations("/WEB-INF/classes/favicon.ico").setCachePeriod(24 * 60 * 60);
        
//        FunctionLogSupport.end("ManageExtensionConfiguration.addResourceHandlers");
        
    }

    /*@Bean
    public Map<String, Map<String, String>> moduleMapping(
            @Value("#{${app.module.mapping}}") final Map<String, Map<String, String>> settings) {
         return settings;
    }*/
    
    /**
     * 使用 Twitter snowflake 的演算法，產生不重複的整數序號，每台機器必須使用不同的 nodeId
     * 這邊預設從 環境變數 (environment variables) APP_NODE_ID 中設定，部署時必須注意
     *
     * @param nodeId
     * @return
     */
    @Bean
    public Snowflake snowflake(@Value("${app.node.id}") int nodeId) {
        log.info("APP_NODE_ID: {}", nodeId);
        return new Snowflake(nodeId);
    }

}
