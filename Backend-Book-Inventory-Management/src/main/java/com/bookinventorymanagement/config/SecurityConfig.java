package com.bookinventorymanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationEntryPoint point;
	@Autowired
	private JwtAuthenticationFilter filter;
//	@Autowired
//	private UserDetailsService userDetailsService;
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests().requestMatchers("/auth/register", "/auth/login","/book/category/{category}")
				.permitAll()

				.requestMatchers("/book", "/book/{isbn}", "/book/title/{title}", "/book/publisher/{publisherId}",
						 "/bookcondition/{ranks}", "/author/{authorId}",
						"/author/firstname/{firstname}", "/author/lastname/{lastname}", "/author/books/{authorId}",
						"/state", "/state/{stateCode}", "/publisher", "/category/{catId}", "/bookreview/**")
				.hasAnyAuthority("Guest", "RegisteredUser", "StoreOwner", "Admin")

				.requestMatchers("/user/update/firstname/{userId}", "/user/update/lastname/{userId}",
						"/user/update/phonenumber/{userId}")
				.hasAnyAuthority("RegisteredUser", "Admin")

				.requestMatchers("/bookcondition/**", "/purchaselog/**", "/user/{userId}","/reviewer/{id}", "/inventory/**")
				.hasAnyAuthority("StoreOwner", "Admin")

				.anyRequest().hasAuthority("Admin")
				.and().exceptionHandling(ex -> ex.authenticationEntryPoint(point))
				.sessionManagement(Session -> Session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider);

		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

		return http.build();

	}

}
//