package com.thalescoding.registerAPI.config;

import com.thalescoding.registerAPI.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        final HttpSecurity config = http
                .authorizeHttpRequests(requests ->
                        requests.requestMatchers("/**")
                                .hasAnyRole("USER", "MANAGER", "ADMIN", "SUPER_ADMIN")
                                .anyRequest()
                                .authenticated()
                )
                //.formLogin(AbstractAuthenticationFilterConfigurer::permitAll) //Authorization pelo formulário
                .httpBasic()//Aqui estamos usando basic auth (login e senha) ao invés do formLogin()
                .and()
                //.addFilter(new JwtAuthenticationFilter(authenticationManagerBean(),"seu_jwt_secret_aqui", 86400000))
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        //Autenticação de segurança em Banco
        auth.userDetailsService(userDetailService)
                .passwordEncoder(new BCryptPasswordEncoder());


        //Autenticação de segurança em memória
//        auth.inMemoryAuthentication()
//                .withUser("john@gmail.com")
//                .password(new BCryptPasswordEncoder().encode("$2a$12$ayIKpNFysmse/wbLZUXFMupPXcb8SkH373bENmbSR/f8CgvOE.A0C"))
//                .roles("ADMIN");

        //john@gmail.com
        //123
    }


    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED);
    }

}

