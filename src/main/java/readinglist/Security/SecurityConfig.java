package readinglist.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import readinglist.ReaderRepository;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private ReaderRepository readerRepository;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .headers().frameOptions().disable()
        .and()
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/h2-console/*").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .successHandler(myAuthenticationSuccessHandler());
  }

  @Override
  protected void configure(
      AuthenticationManagerBuilder auth) throws Exception {
    auth
        .userDetailsService(new UserDetailsService() {
          @Override
          public UserDetails loadUserByUsername(String username)
              throws UsernameNotFoundException {
            UserDetails userDetails = readerRepository.findById(username).orElse(null);
            if (userDetails != null) {
              return userDetails;
            }
            throw new UsernameNotFoundException("User '" + username + "' not found.");
          }
        });
  }

  @Bean
  public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
    return new MySimpleUrlAuthenticationSuccessHandler();
  }
}