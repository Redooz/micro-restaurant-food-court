package com.pragma.microservicefoodcourt.configuration.security;

import com.pragma.microservicefoodcourt.configuration.security.filter.JwtAuthenticationFilter;
import com.pragma.microservicefoodcourt.domain.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
                .antMatchers(HttpMethod.POST,"/categories/").hasAnyAuthority(Role.ADMIN.name(), Role.OWNER.name())
                .antMatchers(HttpMethod.GET,"/categories/").permitAll()
                .antMatchers(HttpMethod.POST,"/restaurants/").hasAnyAuthority(Role.ADMIN.name())
                .antMatchers(HttpMethod.GET,"/restaurants/").permitAll()
                .antMatchers(HttpMethod.POST,"/dishes/").hasAnyAuthority(Role.OWNER.name())
                .antMatchers(HttpMethod.PATCH,"/dishes/").hasAnyAuthority(Role.OWNER.name())
                .antMatchers(HttpMethod.GET,"/dishes/").permitAll()
                .antMatchers(HttpMethod.POST,"/orders/").hasAnyAuthority(Role.CUSTOMER.name())
                .antMatchers(HttpMethod.PATCH,"/orders/*/assign").hasAnyAuthority(Role.EMPLOYEE.name())
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
