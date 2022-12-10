package com.warsaw.hospital.config;

import com.warsaw.hospital.auth.config.AuthEntryPointJwt;
import com.warsaw.hospital.auth.config.JwtAuthFilter;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.warsaw.hospital.auth.utils.JwtUtil.ADMIN;
import static com.warsaw.hospital.auth.utils.JwtUtil.USER;

@Profile("!testing")
@Configuration
public class WebSecurityConfig {
  public static final String[] AUTH_WHITELIST = {
    "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/api/v1/auth/**",
  };
  public static final String[] ADMIN_URL_LIST = {
    //    "/api/admin/**",
    //          "/api/v1/admin/**",
    //          "/admin**",
    //          "/api/admin/v1**"
  };

  private final AuthEntryPointJwt unauthorizedHandler;
  private final JwtAuthFilter jwtAuthFilter;

  public WebSecurityConfig(AuthEntryPointJwt unauthorizedHandler, JwtAuthFilter jwtAuthFilter) {
    this.unauthorizedHandler = unauthorizedHandler;
    this.jwtAuthFilter = jwtAuthFilter;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .cors()
        .disable()
        .exceptionHandling()
        .authenticationEntryPoint(unauthorizedHandler)
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers("*", "**", "/**")
        .permitAll()
        .antMatchers(ADMIN_URL_LIST)
        .hasAnyAuthority(ADMIN)
        .anyRequest()
        .hasAnyAuthority(USER)
        .and()
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(@NotNull CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("*")
            .allowedHeaders("*")
            .allowedHeaders("*")
            .exposedHeaders("*");
      }
    };
  }
}
