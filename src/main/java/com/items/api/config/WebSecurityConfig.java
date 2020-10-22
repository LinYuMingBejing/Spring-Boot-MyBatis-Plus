package com.items.api.config;

import com.items.api.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 開啟spring security功能
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    SessionRegistry sessionRegistry;

    @Autowired
    CustomUserService customUserService;

    @Bean
    public SessionRegistry getSessionRegistry(){
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        // 解決 SpringSecurityFrame 'X-Frame-Option' to deny
        http.headers().frameOptions().disable();
        http.csrf().disable(); // 開啟跨域
        http
                .authorizeRequests() // 驗證請求
                .antMatchers("/css/**", "/dist/**", "/js/**").permitAll()
                .antMatchers("/user/**").permitAll() // 放行部分合法路徑
                .anyRequest().authenticated() // 其他路徑需要驗證
                .and()
                .formLogin()
                .loginPage("/a/login") // 指定登入表單視圖路徑
                .defaultSuccessUrl("/a/index") // 默認登入成功後跳轉的頁面
                .failureUrl("/a/login") //指定失敗重新跳轉至登入頁面
                .and().sessionManagement()
                .maximumSessions(1) // 表示系統中間同一個帳戶登入的次數
                .sessionRegistry(sessionRegistry)
                .and().and()
                .logout()
                .invalidateHttpSession(true) // 使session失效
                .clearAuthentication(true) // 清除所有認證訊息
                .permitAll();// ;
    }
    // AuthenticationManagerBuilder 驗證前台提交的數據
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        // BCryptPasswordEncoder 指定密碼的驗證規則
        auth.userDetailsService(customUserService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
