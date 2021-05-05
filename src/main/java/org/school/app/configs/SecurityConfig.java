package org.school.app.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/", "/index.html").permitAll()
        .antMatchers("/student").authenticated()
        .antMatchers("/students").hasRole("ADMIN")
        .and()
        .formLogin()
        .and().logout().and()
        .httpBasic();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(User.withDefaultPasswordEncoder()
        .username("a")
        .password("a")
        .roles("ADMIN")
        .build());
    manager.createUser(User.withDefaultPasswordEncoder()
        .username("s")
        .password("s")
        .roles("STUDENT")
        .build());
    return manager;
  }
}