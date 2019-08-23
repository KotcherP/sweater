package com.example.sweater.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //включаем у объекта http авторизацию для всех остальных ,кроме главной
        http
                .authorizeRequests()
                //заходит на главную страницу с полными правами
                  .antMatchers("/","/registration").permitAll()
                //требуется авторизация на остальных страницах
                  .anyRequest().authenticated()
                .and()
                //форма логин
                  .formLogin()
                  .loginPage("/login")
                  .permitAll()
                .and()
                  .logout()
                  .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select userName,password,active from usr where userName = ?") //запрос получает юзеров
        .usersByUsernameQuery("select u.userName,ur.roles from usr u inner join user_role ur " +
                "on u.id = ur.user_id where userName = ?"); //запрос помогает спрингу получить список пользователей с их ролями
    }

    //    //создает пользователей
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("u")
//                        .password("l")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
}