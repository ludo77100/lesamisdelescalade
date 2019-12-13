package com.ludo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ludo.enums.RoleEnum;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	

  private final String adminRole = RoleEnum.ADMINISTRATOR.name();

  private final UserDetailsService userDetailsService;

  @Autowired
  public WebSecurityConfig(UserDetailsService userDetailsService) {
      this.userDetailsService = userDetailsService;
  }

  @Bean(name = "passwordEncoder")
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }

  @Autowired
  public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
              .antMatchers("/auth**").authenticated()
              .antMatchers("/auth/admin**").hasAuthority(adminRole)
              .anyRequest().permitAll()
          .and()
              .formLogin().loginPage("/login").defaultSuccessUrl("/index").failureUrl("/login")
              .usernameParameter("pseudo").passwordParameter("motDePass")
          .and()
              .logout().invalidateHttpSession(true)
              .logoutUrl("/logout")
              .logoutSuccessUrl("/login")
          .and()
              .csrf()
          .and()
              .sessionManagement().maximumSessions(1).expiredUrl("/login");
  }
}
