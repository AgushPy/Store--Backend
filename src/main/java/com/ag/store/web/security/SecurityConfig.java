package com.ag.store.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ag.store.domain.service.StoreUserDetailsService;
import com.ag.store.web.security.filter.JsonWebTokenFilterRequest;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private StoreUserDetailsService storeDetailsService;
    
    @Autowired
    private JsonWebTokenFilterRequest jwtFilterRequest;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(storeDetailsService);
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {    
        web.ignoring().antMatchers("/v2/api-docs/**");
        web.ignoring().antMatchers("/swagger.json");
        web.ignoring().antMatchers("/swagger-ui.html");
        web.ignoring().antMatchers("/swagger-resources/**");
        web.ignoring().antMatchers("/webjars/**");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
/*        Con estas linea se desauthoriza el inicio de sesion 
        para enviar la auntenficacion y se vuelve a reestablecer
        para las demas el inicio de sesion, pero luego este se desactiva 
        ya que el jwt se encargara del filtrado de sesiones */
        http.csrf().disable().authorizeHttpRequests().antMatchers("/**/authenticate").permitAll()
             .anyRequest().authenticated().and().sessionManagement()
             .sessionCreationPolicy(SessionCreationPolicy.STATELESS);   
        http.addFilterBefore(jwtFilterRequest,UsernamePasswordAuthenticationFilter.class);
    }
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
    
    
}
