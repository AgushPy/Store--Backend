package com.ag.store.web.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ag.store.domain.service.StoreUserDetailsService;
import com.ag.store.web.security.JWTUtil;

@Component
public class JsonWebTokenFilterRequest extends OncePerRequestFilter {
    @Autowired
    private JWTUtil jwtUtil;
    
    @Autowired
    private StoreUserDetailsService storeUserDetailsService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            /*Con esto lo que hago es que como valido Bearer > Es lo que esta enfrente de el jwt 
            en el request se cuentan las 6 letras y un espacio y se toma el resto
            ques lo que importa para el jwt*/
            String jwt = authorizationHeader.substring(7);
            String username = jwtUtil.extractUsername(jwt);
            
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                
                //Se valida que exista este usuario en el sistema de autentificacion
                UserDetails userDetails =  storeUserDetailsService.loadUserByUsername(username);
                if(jwtUtil.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        // TODO Auto-generated method stub
        filterChain.doFilter(request, response);
    }
    
}
