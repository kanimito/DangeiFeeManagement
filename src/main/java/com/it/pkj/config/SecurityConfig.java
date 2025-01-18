package com.it.pkj.config;

import com.it.pkj.service.impl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/partymembers/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers("/paymentrecords/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers("/paymentstandards/**").hasRole("ADMIN")
//                .antMatchers("/regionentities/**").hasRole("ADMIN")
//                .antMatchers("/users/**").hasRole("ADMIN")
//                .and()
//                .formLogin()
//                .and()
//                .httpBasic();
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .and()
//                .withUser("user")
//                .password(passwordEncoder().encode("user"))
//                .roles("USER");
//    }
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
//将编写的UserDetailsService注入进来
        provider.setUserDetailsService(myUserDetailsService);
//将使用的密码编译器加入进来
        provider.setPasswordEncoder(passwordEncoder);
//将provider放置到AuthenticationManager 中
        ProviderManager providerManager=new ProviderManager(provider);
        return providerManager;
    }
    /*
     * 在security安全框架中，提供了若干密码解析器实现类型。
     * 其中BCryptPasswordEncoder 叫强散列加密。可以保证相同的明文，多次加密后，
     * 密码有相同的散列数据，而不是相同的结果。
     * 匹配时，是基于相同的散列数据做的匹配。
     * Spring Security 推荐使用 BCryptPasswordEncoder 作为密码加密和解析器。
     * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}