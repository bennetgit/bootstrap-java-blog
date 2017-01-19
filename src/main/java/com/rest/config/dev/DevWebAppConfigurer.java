package com.rest.config.dev;

import com.rest.intercetors.ExecutionInterceptor;
import com.rest.local.MyLocaleCookieLocaleResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 * @Author bruce.ge
 * @Date 2017/1/17
 * @Description
 */
@Configuration
@Profile("dev")
public class DevWebAppConfigurer extends WebMvcConfigurerAdapter {
    @Autowired
    @Qualifier("authInterceptor")
    private HandlerInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //remove page will forward. //how to know the forward request real time cost?
        registry.addInterceptor(new ExecutionInterceptor());
        registry.addInterceptor(authInterceptor);
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        registry.addInterceptor(localeChangeInterceptor);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }



    @Bean(name = "localeResolver")
    public LocaleResolver localeResolver() {
        MyLocaleCookieLocaleResolver myLocaleCookieLocaleResolver = new MyLocaleCookieLocaleResolver();
        myLocaleCookieLocaleResolver.setCookieName("LOCALE_KEY");
        return myLocaleCookieLocaleResolver;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("MessagesBundle");
        return resourceBundleMessageSource;
    }
}