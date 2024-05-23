package cn.aicnn.chatssespringboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourcesConfig implements WebMvcConfigurer
{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {

        // 解决静态资源无法访问，注意这里不是一成不变的
        // 放行后缀只有一个的
        // 所有以 "/static/" 开头的 URL 路径将会映射到 classpath 下的 "/static/" 目录中的静态资源
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }



}
