package com.lcwd.electronic.store.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;
    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser(Principal principal){
        String userName=principal.getName();
        return ResponseEntity.ok(userDetailsService.loadUserByUsername(userName));
    }



}
