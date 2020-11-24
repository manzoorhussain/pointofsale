package com.pos.pos.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pos.pos.filters.RequestFilter;

@Configuration
public class FilterConfiguration {
	
	
	
	private final RequestFilter dateLoggingFilter;

    @Autowired
    public FilterConfiguration(RequestFilter dateLoggingFilter) {
        this.dateLoggingFilter = dateLoggingFilter;
    }

    @Bean
    public FilterRegistrationBean<RequestFilter> dateLoggingFilterRegistration() {
        FilterRegistrationBean<RequestFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(dateLoggingFilter);
        filterRegistrationBean.setUrlPatterns(Collections.singletonList("/*"));
       
        
        return filterRegistrationBean;
    }

}
