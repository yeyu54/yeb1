package com.yeyu.config.security;

import com.yeyu.config.security.component.JwtTokenFilter;
import com.yeyu.config.security.component.RestWithoutPermission;
import com.yeyu.config.security.component.Restprint;
import com.yeyu.pojo.Admin;
import com.yeyu.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author 13474
 * @Package com.yeyu.config
 * @date 2021/11/1620:53
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IAdminService adminService;
    @Autowired
    private Restprint restprint;
    @Autowired
    private RestWithoutPermission restWithoutPermission;


    protected  void configure(AuthenticationManagerBuilder auth) throws  Exception{
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                //基于token 不要session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .headers()
                .cacheControl();
        //拦截器
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义未授权和未登录结果返回
        http.exceptionHandling()
                .accessDeniedHandler(restWithoutPermission)
                .authenticationEntryPoint(restprint);
    }
    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers(
                "/doc.html"
                ,"/login",
                "/css/**",
                "/js/**",
                "/index.html",
                "/webjars/**",
                "/logout"
                ,"/captcha"
                ,"/swagger-resources/**"
                ,"/v2/api-docs/**"

        );
    }
    @Override
    @Bean
    public UserDetailsService userDetailsService(){
        return username ->{
         Admin admin =   adminService.getAdminusername(username);
            if (null != admin){
                return admin;
            }
            return null;
        };


    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public JwtTokenFilter jwtTokenFilter(){
        return  new JwtTokenFilter();
    }
}
