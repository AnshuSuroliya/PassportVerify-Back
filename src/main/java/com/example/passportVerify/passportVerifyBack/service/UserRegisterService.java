package com.example.passportVerify.passportVerifyBack.service;

import com.example.passportVerify.passportVerifyBack.entity.User;
import com.example.passportVerify.passportVerifyBack.repository.UserRepository;
import com.example.passportVerify.passportVerifyBack.request.Login;
import com.example.passportVerify.passportVerifyBack.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;

    public String signUp(User user){
        User user1=userRepository.findByEmail(user.getEmail());
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        if(user1==null){
            User u=new User();
            u.setFirstName(user.getFirstName());
            u.setLastName(user.getLastName());
            u.setEmail(user.getEmail());
            u.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            u.setPhoneNumber(user.getPhoneNumber());
            userRepository.save(u);
            return "User Registered Successfully";
        }
        else return "User already Present";
    }
    public LoginResponse signIn(Login login) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        User user = userRepository.findByEmail(login.getEmail());
        if (authentication.isAuthenticated()) {
            String jwt = (jwtService.generateToken(login.getEmail()));
            Long id = user.getId();
            LoginResponse loginResponse = new LoginResponse(jwt, id);
            return loginResponse;
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
