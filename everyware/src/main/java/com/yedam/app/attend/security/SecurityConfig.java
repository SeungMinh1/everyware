package com.yedam.app.attend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChin(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
				.antMatchers("/", "/main", "/login","/css/**", "/fonts/**", "/images/**", "/js/**", "/dist/**", "/plugins/**").permitAll()
				.antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
				.antMatchers("/worker/**").hasAnyRole("WORKER", "ADMIN")
				.antMatchers("/admin/**").hasAnyAuthority("ROLE ADMIN")
				.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/login").usernameParameter("id")
			.failureHandler(new UserLoginFailHandler())//인증에 실패한 후 실행 하는 객체(개발자가 생성)
			.defaultSuccessUrl("/main")
			.and()
			.logout()
				.logoutSuccessUrl("/login")
				.and()
				.csrf().disable();
		
		return http.build();
	}

}
