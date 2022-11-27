package com.example.L14springsecuritydemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    protected AppUserDetailService appUserDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        auth.inMemoryAuthentication().withUser("rishabh").password("$2a$10$sPheJfJg74M/G3APrHCcuu0TcRmFHslwUg12Jh0p1YVcEuE2cZb6.").authorities("user")
//                .and().withUser("ravi").password("$2a$10$3BeXMDb5ROFE.CmBwTRBSuJCd.1dfiqG9i6yMaPXDra/t0sQxgSeS").authorities("admin");
        auth.userDetailsService(appUserDetailService);
    }


    @Bean
    public PasswordEncoder getPasswordEncoder(){
       // return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("12345"));
        //$2a$10$t8.v14ATNyK7czzmtZaXyuuKorkHOi2Y7fwX8KMEIaJS.yTluUm.S

    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests().antMatchers("/admin/*").hasAuthority("admin")
                .antMatchers("/user/*").hasAnyAuthority("user","admin");
        httpSecurity.formLogin();
        httpSecurity.httpBasic();
        httpSecurity.csrf().disable();
    }

}
