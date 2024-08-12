package com.visiontech.school_management.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.visiontech.school_management.services.CustomUserService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	/*
	 * @Autowired private CustomSuccessHandler customSuccessHandler;
	 */

	@Autowired
	private CustomUserService customUserService;

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(c -> c.disable())

				.authorizeHttpRequests(
						request ->
						request.requestMatchers("/","/register", "/css/**", "/js/**", "/images/**")
						.permitAll()
						.anyRequest().authenticated())
				.formLogin(form -> 
				form.loginPage("/login")
				.loginProcessingUrl("/login")
						// Use this ".successHandler(customSuccessHandler)" when u want to use custom pages based on Roles
				.defaultSuccessUrl("/",true)
				.permitAll())
				.logout(form ->
				form.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout")
				.permitAll()).rememberMe(c->c.rememberMeParameter("remember-me")).httpBasic();
		
		

		return http.build();

	}

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService).passwordEncoder(passwordEncoder());
	}

}