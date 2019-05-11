package pl.jstk.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import pl.jstk.constants.ViewNames;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/webjars/**","/img/**","/css/**").permitAll()
                .antMatchers("/books/deleteBook").hasRole("ADMIN")
                .antMatchers("/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/login*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll();
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();

        httpSecurity.exceptionHandling().accessDeniedPage("/403");
        httpSecurity.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password("{noop}admin").roles("ADMIN")
                .and().withUser("user").password("{noop}user").roles("USER");
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

}
