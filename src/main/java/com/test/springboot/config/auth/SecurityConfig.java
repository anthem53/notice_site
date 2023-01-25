package com.test.springboot.config.auth;


import com.test.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .headers().frameOptions().disable()
        .and()
            .authorizeRequests()
            .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
            .antMatchers("/", "/posts/inquiry/**", "/login","/search/**").permitAll() // 설정된 url은 인증되지 않더라도 누구든 접근 가능
            .anyRequest().authenticated()
        .and()
            .logout()
            .logoutSuccessUrl("/")
        .and()
            .oauth2Login()
            .userInfoEndpoint()
            .userService(customOAuth2UserService);
    }
}
