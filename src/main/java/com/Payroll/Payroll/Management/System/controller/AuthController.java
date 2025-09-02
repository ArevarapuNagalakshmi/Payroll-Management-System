package com.Payroll.Payroll.Management.System.controller;

import com.Payroll.Payroll.Management.System.config.JwtTokenProvider;
import com.Payroll.Payroll.Management.System.dto.AuthRequest;
import com.Payroll.Payroll.Management.System.dto.AuthResponse;
import com.Payroll.Payroll.Management.System.entity.User;
import com.Payroll.Payroll.Management.System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            // Authenticate user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            // Load user details
            UserDetails userDetails = userService.loadUserByUsername(request.getUsername());

            // Generate token
            String token = jwtTokenProvider.generateToken(userDetails.getUsername());

            // Optionally, you can fetch the full User entity for additional info
            User user = userService.getUserByUsername(userDetails.getUsername());

            return ResponseEntity.ok(new AuthResponse(token, user));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid username/password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        // Save user using UserService
        User savedUser = userService.createUser(user);
        return ResponseEntity.ok(savedUser);
    }
}
