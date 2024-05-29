package com.foodapi.demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.foodapi.demo.jwt.JwtFilter;
import com.foodapi.demo.services.CustomUserDetail;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class Security {
    @Autowired
    private CustomUserDetail customUserDetail;

    public Security(CustomUserDetail customUserDetail){
        this.customUserDetail=customUserDetail;
    }

    @Bean
    public JwtFilter jwtTokenFilter(){
        return new JwtFilter();
    }

     @PersistenceContext
    private EntityManager entityManager;
    
    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception{
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetail);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authenticationProvider);
        
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*")); // Hoặc chỉ định `http://localhost:4200` thay vì `*` để hạn chế
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("*"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf((csrf)->csrf.disable())
                    .cors(cors->cors.configurationSource(corsConfigurationSource()))
                    .authorizeHttpRequests(author->author
                                                        .requestMatchers("products/delete/**")
                                                        .hasAnyAuthority("SHOPKEEPER","ADMIN")
                                                        .requestMatchers("/products/add")
                                                        .hasAuthority("SHOPKEEPER")
                                                        .requestMatchers("shop/register/**","/auth/test","/comment/**")
                                                        .hasAnyAuthority("USER")
                                                        .requestMatchers(HttpMethod.POST, "/feedback/**","/upload/img","/like/**","/post/**","/shop/**","/user/**")
                                                        .hasAnyAuthority("USER")
                                                        .requestMatchers("/ws/**").permitAll()
                                                        .anyRequest().permitAll()
                                                        
                                                        
        );
        

        httpSecurity.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}   
