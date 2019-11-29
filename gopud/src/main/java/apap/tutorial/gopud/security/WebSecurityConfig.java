package apap.tutorial.gopud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    // form untuk login dapat diakses siapapun
    // /login perlu diautentikasi
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/api/v1/**").permitAll()
//                .antMatchers("/restoran/**").hasAnyAuthority("MERCHANT")
//                .antMatchers("/restoran/**").hasAnyAuthority("ADMIN")
//                .antMatchers("/menu/**").hasAnyAuthority("MERCHANT")
//                .antMatchers("/api/v1/**").hasAnyAuthority("ADMIN")
//                .antMatchers("/user/**").hasAnyAuthority("ADMIN")
//                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll()
                .and()
                .cors();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    // menyimpan autentikasi in-memory dengan username "nadiem", password "makarim", dan rolenya "USER"
    public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .passwordEncoder(encoder())
            .withUser("nadiem").password(encoder().encode("makarim"))
            .roles("USER");
    }

//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
//    }
}
