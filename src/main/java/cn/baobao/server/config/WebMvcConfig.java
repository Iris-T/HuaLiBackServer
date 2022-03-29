package cn.baobao.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Iris 2022/3/19
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${file.image.plog}")
    private String plogPath;
    @Value("${file.image.good}")
    private String goodPath;
    @Value("${file.image.errinfo}")
    private String errorinfoPath;

    /**
     * 添加资源加载
     * @param registry
     * @see ResourceHandlerRegistry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        // 解决 SWAGGER 404报错
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/pi/**").addResourceLocations("file:"+plogPath);
        registry.addResourceHandler("/gi/**").addResourceLocations("file:"+goodPath);
        registry.addResourceHandler("/ei/**").addResourceLocations("file:"+errorinfoPath);
    }
}
