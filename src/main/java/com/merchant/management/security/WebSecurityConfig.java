package com.merchant.management.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class WebSecurityConfig implements WebMvcConfigurer{
	
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	private  AuthenticationProvider authenticationProvider;
    
	 @Autowired
	    public WebSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
	                             AuthenticationProvider authenticationProvider) {
	        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	        this.authenticationProvider = authenticationProvider;
	    }
	
	 
	 
//	
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                // disable CSRF, http basic, form login
//                .csrf().disable() 
//                .httpBasic().disable() 
//                .formLogin().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
//                .and().exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint());
//        return http.build();
//    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
		    .cors()
		    .and()
    	    .csrf()
    	    .disable()
    	    .authorizeHttpRequests()
    	    .requestMatchers("/api/v1/auth/**")
    	    .permitAll()
    	    .anyRequest()
    	    .authenticated()
    	    .and()
    	    .sessionManagement()
    	    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    	    .and()
    	    .authenticationProvider(authenticationProvider)
    	    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)	;
    	
    	return http.build();
    }
    
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/v1/auth/**")// Adjust the mapping as necessary
                .allowedOrigins("http://localhost:4200","https://merchant-angular-app-983146727685.asia-south1.run.app","http://34.71.241.98") // Your Angular app's origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Specify allowed methods
                .allowCredentials(true); // If you need to include credentials
        
        registry.addMapping("/services/v1/products/**")// Adjust the mapping as necessary
        .allowedOrigins("http://localhost:4200","https://merchant-angular-app-983146727685.asia-south1.run.app","http://34.71.241.98") // Your Angular app's origin
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Specify allowed methods
        .allowCredentials(true);
        
        registry.addMapping("/services/v1/merchant/**")// Adjust the mapping as necessary
        .allowedOrigins("http://localhost:4200","https://merchant-angular-app-983146727685.asia-south1.run.app","http://34.71.241.98") // Your Angular app's origin
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Specify allowed methods
        .allowCredentials(true);
        
        registry.addMapping("/services/v1/billing/**")// Adjust the mapping as necessary
        .allowedOrigins("http://localhost:4200","https://merchant-angular-app-983146727685.asia-south1.run.app","http://34.71.241.98") // Your Angular app's origin
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Specify allowed methods
        .allowCredentials(true);// If you need to include credentials
        
        registry.addMapping("/services/v1/customer/**")// Adjust the mapping as necessary
        .allowedOrigins("http://localhost:4200","https://merchant-angular-app-983146727685.asia-south1.run.app","http://34.71.241.98") // Your Angular app's origin
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Specify allowed methods
        .allowCredentials(true);
    }


}
