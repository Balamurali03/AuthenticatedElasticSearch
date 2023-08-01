package com.project.StudentApp.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.StudentApp.Filter.JwtFilter;
import com.project.StudentApp.Service.CustomDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private CustomDetailsServiceImpl customDetailsServiceImpl;

	 @Autowired
	    private JwtFilter jwtFilter;
	@Bean
	 AuthenticationProvider authenticationProvider() {
		 DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		 authenticationProvider.setUserDetailsService(customDetailsServiceImpl);
		 authenticationProvider.setPasswordEncoder( new BCryptPasswordEncoder());
		 return authenticationProvider;
	 }
	
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    	System.out.println("auth");
        return authenticationConfiguration.getAuthenticationManager();
    }
	
	


    @Bean
    public SecurityFilterChain  filterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf->csrf.disable()).authorizeRequests()
//                .requestMatchers("/login","/signUp")
//                .permitAll()
//                .anyRequest()
//                .authenticated().and().sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//                return http.build();
    	http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/login","/signUp").permitAll()
            .anyRequest().authenticated()
        )
        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
      http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
      return http.build();
    	
       

    }
   
                

    
}
