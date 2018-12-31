package com.spring.security.springbootsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	/*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {  
        BCryptPasswordEncoder encoder = passwordEncoder();
        auth.inMemoryAuthentication().withUser("amol").password(encoder.encode("amol123")).roles("USER");  
    }*/
	
	 /*@Bean
     public BCryptPasswordEncoder passwordEncoder() {
         return new BCryptPasswordEncoder();
     }*/

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeRequests()
                .anyRequest()
                .fullyAuthenticated()
                //.antMatchers("**/rest/*")
                .and()
                //.addFilterBefore(customFilter(), BasicAuthenticationFilter.class)
                .httpBasic();
        httpSecurity.csrf().disable();

    }
    

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("amol")
                .password("amol123")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
    
    
}
