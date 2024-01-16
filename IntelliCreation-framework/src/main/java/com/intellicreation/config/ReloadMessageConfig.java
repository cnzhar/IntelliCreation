package com.intellicreation.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * 配置 spring security 的报错提示语言
 * @author za
 */
@Configuration
public class ReloadMessageConfig {

    /**
     * 修改 MessageSource 的 basename 为中文资源文件的名称
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages_zh_CN");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * 修改 LocaleResolver 的默认语言为中文。
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return resolver;
    }
}
