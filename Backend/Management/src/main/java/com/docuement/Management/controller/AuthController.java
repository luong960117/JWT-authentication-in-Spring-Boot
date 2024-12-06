package com.docuement.Management.controller;

import com.docuement.Management.Auth.JwtHelper;
import com.docuement.Management.config.AuthConfig;
import com.docuement.Management.dto.req.JWTRequest;
import com.docuement.Management.dto.req.UserCreateDto;
import com.docuement.Management.dto.resp.JWTResponse;
import com.docuement.Management.dto.resp.UserResponseDto;
import com.docuement.Management.response.JSONResponse;
import com.docuement.Management.response.ResponseStatus;
import com.docuement.Management.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthConfig authConfig;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtHelper helper;


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
        MediaType.APPLICATION_ATOM_XML_VALUE,
        MediaType.APPLICATION_JSON_VALUE
    })
    public String register(UserCreateDto userCreateDto) {
        System.out.println("auth controller");
//        UserResponseDto userResponseDto = this.userService.register(userCreateDto);
//        jsonResponse.setStatus(ResponseStatus.FAILURE);
//        jsonResponse.setMessage("succeed");
        return  "Test response";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<JWTResponse> login(JWTRequest jwtRequest) {
        this.doAuthenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.helper.generateToken(userDetails);
        JWTResponse jwtResponse = JWTResponse.builder().token(token).build();
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    private void doAuthenticate(String username, String password) {
        System.out.println("Login Info");
        System.out.println(username);
        System.out.println(password);
        System.out.println("------");
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        System.out.println(authentication.toString()+"  do authenticarte");
        try {
            System.out.println("Started");
            manager.authenticate(authentication);
            System.out.println("Eneded");
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("Authentication successful for user: " + username);


        } catch (BadCredentialsException e) {
            System.out.println("Authentication not-successful for user: " + username);
            throw new BadCredentialsException(" Invalid Username or Password  !!");

        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler(BadCredentialsException ex) {
        return "Credentials Invalid !!";
    }
}
