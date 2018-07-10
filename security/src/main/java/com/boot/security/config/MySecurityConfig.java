package com.boot.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

/**
 * Created by Janus on 2018/7/10.
 */
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private DataSource dataSource;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 定制请求的授权规则
        http.authorizeRequests()
            .antMatchers("/css/**", "/").permitAll()
            .antMatchers("/level1/**").hasRole("VIP1")
            .antMatchers("/level2/**").hasRole("VIP2")
            .antMatchers("/level3/**").hasRole("VIP3");
        // 开启自动配置的登录功能
        http.formLogin().usernameParameter("name").passwordParameter("pwd").loginPage("/userlogin");

        // 开启自动配置的注销功能,默认重定向到/logout?success,修改为"/"
        http.logout().logoutSuccessUrl("/");

        //开启自动配置的Remember me
        http.rememberMe().rememberMeParameter("remember");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("zhangsan").password("123456").roles("VIP1","VIP2")
                .and()
                .withUser("lisi").password("123456").roles("VIP3","VIP2")
                .and()
                .withUser("wangwu").password("123456").roles("VIP3","VIP1");
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select username,password, enabled from users where username = ?")
//                .authoritiesByUsernameQuery("select username, role from user_roles where username = ?");
    }




}
