package com.pickyourcpu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser( "admin" )
                .password( "1234566" )
                .roles( "ADMIN" );
    }

    @Override
    public void configure( WebSecurity web ) throws Exception {
        web.ignoring().antMatchers( "/", "/resources/**", "/static/**", "/public/**", "/webui/**", "/h2-console/**"
                , "/configuration/**", "/swagger-ui/**", "/swagger-resources/**", "/api-docs", "/api-docs/**", "/v2/api-docs/**"
                , "/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.gif", "/**/*.svg", "/**/*.ico", "/**/*.ttf", "/**/*.woff" );
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http
                .authorizeRequests()
                .antMatchers( "/**" ).permitAll()
                .antMatchers( "/quan-tri-vien/**" ).hasRole( "ADMIN" )
                .and()
                .formLogin()
                .loginPage( "/dang-nhap" )
                .usernameParameter( "username" )
                .passwordParameter( "password" )
                .defaultSuccessUrl( "/quan-tri-vien/crawl" )
                .and()
                .logout()
                .logoutRequestMatcher( new AntPathRequestMatcher( "/dang-xuat" ) )
                .logoutSuccessUrl( "/" )
                .and()
                .exceptionHandling()
                .accessDeniedPage( "/access-denied" )
                .and()
                .anonymous();
    }
}
