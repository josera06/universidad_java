package com.spring.uni.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	protected void configure (AuthenticationManagerBuilder build) throws Exception {

        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }
	
    /*@Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("admin")           
            .password("{noop}123")
            .roles("ADMIN","USER")           
            .build();   
       
        UserDetails user2 = User.withUsername("user")           
            .password("{noop}123")
            .roles("USER")           
            .build();           

        return new InMemoryUserDetailsManager(user,user2);

    }*/
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    	//, AuthenticationManager authenticationManager

            http.authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/editar/**", "/agregar/**", "/eliminar")
                            .hasRole("ADMIN")
                            .requestMatchers("/").hasAnyRole("ADMIN", "USER")
                            .anyRequest().authenticated()
                    )
                    .formLogin(form -> form.loginPage("/login")
                            .defaultSuccessUrl("/", true)
                            .permitAll()
                    )
                    .logout(logout -> logout                                                
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/login")
                            .invalidateHttpSession(true)
                    )
                    .exceptionHandling().accessDeniedPage("/errores/403");
            
            return http.build();
    }
}
