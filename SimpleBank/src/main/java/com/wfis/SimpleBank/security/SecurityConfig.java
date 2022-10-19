package com.wfis.SimpleBank.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private DataSource dataSource;

  @Override
  public void configure(AuthenticationManagerBuilder auth)
      throws Exception {

    auth.jdbcAuthentication()
        .dataSource(dataSource)
        .withDefaultSchema()
        .withUser(User.withUsername("user")
            .password(passwordEncoder().encode("password"))
            .roles("USER"))
        .withUser(User.withUsername("admin")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN"));;
  }

  @Bean
  public UserDetailsService jdbcUserDetailsService(DataSource dataSource) {
    return new JdbcUserDetailsManager(dataSource);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {

    //@formatter:off
    http.headers().frameOptions().disable();
    http.authorizeRequests()
        .antMatchers("/login").permitAll()
            .antMatchers("/h2/**").permitAll()
        .antMatchers("/initialize").permitAll()
        .antMatchers(HttpMethod.GET, "/account/transfer").hasAnyRole("USER", "ADMIN")
            .antMatchers(HttpMethod.GET, "/account/bonus").hasAnyRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/account/manage").hasAnyRole("ADMIN")
        .antMatchers("/**").hasAnyRole("USER", "ADMIN")
        .and()
          .formLogin()
          .loginPage("/login")
          .loginProcessingUrl("/process-login")
          .defaultSuccessUrl("/home")
          .failureUrl("/login?error=true")
          .permitAll()
        .and()
          .logout()
          .logoutSuccessUrl("/login?logout=true")
          .invalidateHttpSession(true)
          .deleteCookies("JSESSIONID")
          .permitAll()
        .and()
          .csrf()
          .disable();
    //@formatter:on
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring()
        .antMatchers("/resources/**", "/static/**");
  }



/*  @Bean
  AuthenticationSuccessHandler authenticationSuccessHandler() {
    return new CustomAuthenticationSuccessHandler();
  }

  @Bean
  AuthenticationFailureHandler authenticationFailureHandler() {
    return new CustomAuthenticationFailureHandler();
  }*/
}
