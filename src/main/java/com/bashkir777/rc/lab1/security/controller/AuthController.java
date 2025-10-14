package com.bashkir777.rc.lab1.security.controller;

import com.bashkir777.rc.lab1.data.dto.AuthRequestDTO;
import com.bashkir777.rc.lab1.data.dto.JwtResponseDTO;
import com.bashkir777.rc.lab1.data.entity.User;
import com.bashkir777.rc.lab1.data.service.UserService;
import com.bashkir777.rc.lab1.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequestDTO authRequestDTO) {
        User user;
        try {
            user = userService.register(authRequestDTO.getUsername(), authRequestDTO.getPassword());
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(String.format("User %s registered successfully", user.getUsername()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO authRequestDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(),
                            authRequestDTO.getPassword())
            );
            if (authentication.isAuthenticated()) {
                String jwt = jwtService.generateToken(authRequestDTO.getUsername());
                return ResponseEntity.ok(new JwtResponseDTO(jwt));
            } else {
                throw new BadCredentialsException("Invalid username or password");
            }
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

}