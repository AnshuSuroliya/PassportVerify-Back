package com.example.passportVerify.passportVerifyBack.controller;

import com.example.passportVerify.passportVerifyBack.entity.User;
import com.example.passportVerify.passportVerifyBack.repository.UserRepository;
import com.example.passportVerify.passportVerifyBack.request.Login;
import com.example.passportVerify.passportVerifyBack.response.LoginResponse;
import com.example.passportVerify.passportVerifyBack.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {
@Autowired
    UserRepository userRepository;
@Autowired
    UserRegisterService userRegisterService;
@PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody User user){
    return new ResponseEntity<>(userRegisterService.signUp(user),HttpStatus.CREATED);
}
@PostMapping("/signin")
    public ResponseEntity<LoginResponse> login(@RequestBody Login login){
    return new ResponseEntity<>(userRegisterService.signIn(login),HttpStatus.ACCEPTED);
}
    @GetMapping("/details")
    public ResponseEntity<Map<String, Object>> getUserDetails(OAuth2AuthenticationToken authentication) {
        Map<String, Object> userDetails = new HashMap<>();

        userDetails.put("name", authentication.getPrincipal().getAttribute("name"));
        userDetails.put("email", authentication.getPrincipal().getAttribute("email"));
        // Add other user details as required

        return ResponseEntity.ok(userDetails);
    }
}
