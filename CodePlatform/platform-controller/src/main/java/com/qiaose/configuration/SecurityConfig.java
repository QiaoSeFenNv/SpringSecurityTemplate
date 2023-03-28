package com.qiaose.configuration;

import com.qiaose.componet.CustomAccessDeniedHandler;
import com.qiaose.componet.CustomAuthenticationFailureHandler;
import com.qiaose.componet.CustomAuthenticationSuccessHandler;
import com.qiaose.componet.CustomPermissionEvaluator;
import com.qiaose.security.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomPermissionEvaluator customPermissionEvaluator;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers( "/test","/login", "/css/**", "/js/**").anonymous()    //将login页面权限解开
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")    //设置登录路径
                .failureHandler( authenticationFailureHandler())
                .successHandler( authenticationSuccessHandler())
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")      //设置登录路径
                .logoutSuccessUrl("/login")     //设置登录后的页面 ---> 通过 controller来控制跳转到具体的页面，并不会之间跳转的页面上
                .permitAll();

        http.httpBasic() //开启basic认证
                .and()
                .csrf().disable();



    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }





    @Bean
    public MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(customPermissionEvaluator);
        return expressionHandler;
    }

    @Bean
    public CustomAuthenticationFailureHandler authenticationFailureHandler(){
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public CustomAuthenticationSuccessHandler authenticationSuccessHandler(){
        return new CustomAuthenticationSuccessHandler();
    }

}