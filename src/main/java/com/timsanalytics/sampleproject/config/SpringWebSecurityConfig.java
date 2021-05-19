package com.timsanalytics.sampleproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public SpringWebSecurityConfig() {
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()

                // LOCK THE ENDPOINTS DOWN HERE
                .and()
                .authorizeRequests()
//                 .antMatchers("**").permitAll() // WIDE OPEN!!!

                // Front-End Resources
                .antMatchers("/*", "/*/*").permitAll()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/api-docs/**").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()

                // API Endpoints OPEN
                .antMatchers("/api/v1/health-check/**").permitAll()
                .antMatchers("/api/v1/hello/**").permitAll()

                .anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/csrf",
                "/v3/api-docs/**",
                "/api-docs/**",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/swagger-ui/**",
                "/webjars/**");
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        //configuration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        configuration.setAllowedOrigins(
                Arrays.asList(
                        "http://localhost:4200",
                        "http://localhost:4201",
                        "https://timsanalytics.com",
                        "https://crc.timsanalytics.com"
                )
        );
        configuration.setAllowedMethods(
                Arrays.asList(
                        "GET",
                        "POST",
                        "PUT",
                        "PATCH",
                        "DELETE",
                        "OPTIONS"
                )
        );
        configuration.setAllowedHeaders(
                Arrays.asList(
                        "accept",
                        "authorization",
                        "access-control-allow-headers",
                        "access-control-allow-method",
                        "content-type",
                        "origin",
                        "responsetype",
                        "x-auth-token"
//                        "append,delete,entries,foreach,get,has,keys,set,values,Authorization
                )
        );
        configuration.setExposedHeaders(Collections.singletonList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
