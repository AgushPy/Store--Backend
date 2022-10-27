package com.ag.store.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ag.store.domain.Product;
import com.ag.store.domain.dto.AuthenticationRequest;
import com.ag.store.domain.dto.AuthenticationResponse;
import com.ag.store.domain.service.ProductService;
import com.ag.store.domain.service.StoreUserDetailsService;
import com.ag.store.web.security.JWTUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/auth")

public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private StoreUserDetailsService storeUserDetailsService;
    
    @Autowired
    private JWTUtil jwtUtil;
    
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request){

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails userDetails = storeUserDetailsService.loadUserByUsername(request.getUsername());
            String jwt = jwtUtil.generateToken(userDetails); 
            return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
        } catch (BadCredentialsException e) {
            // TODO: handle exception
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        
        
    }
    
   
}
