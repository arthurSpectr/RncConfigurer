package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .csrf().disable()
      .cors()
      .and()
      .antMatcher("/api/**")
      .authorizeRequests()
      .antMatchers("/**").permitAll()
      .anyRequest()
      .authenticated()
      .and()
      .formLogin()
      .loginPage("/api/login")
      .permitAll()
      .successHandler(((request, response, authentication) -> response.setStatus(HttpStatus.OK.value())))
      .failureHandler((request, response, exception) -> response.setStatus(HttpStatus.UNAUTHORIZED.value()))
      .and()
      .logout()
      .logoutUrl("/api/logout")
      .deleteCookies("JSESSIONID")

      .permitAll()
      .logoutSuccessHandler(((request, response, authentication) -> response.setStatus(HttpStatus.OK.value())));


  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    LdapContextSource source = new LdapContextSource();
    source.setUrl("ldaps://testdevad.testdev.ukr:636");
    source.setBase("CN=Users,DC=testdev,DC=ukr");
    source.setUserDn("CN=Administrator,CN=Users,DC=testdev,DC=ukr");
    source.setPassword("P@ssw0rd");
    source.setPooled(true);
    source.setReferral("follow");
    source.afterPropertiesSet();

    auth
      .ldapAuthentication()
      .userSearchFilter("(&(&(objectCategory=person)(objectClass=user))(sAMAccountName={0}))")
      .groupSearchFilter("(&(objectCategory=group)(member={0}))")
      .contextSource(source);
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

    configuration.setAllowCredentials(true);
    configuration.setAllowedOrigins(Collections.singletonList("*"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(Collections.singletonList("*"));
    configuration.setExposedHeaders(Arrays.asList("Content-Disposition"));
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }

}
