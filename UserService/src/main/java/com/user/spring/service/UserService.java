package com.user.spring.service;

import com.user.spring.dto.LoginDto;
import com.user.spring.dto.RegisterDto;
import com.user.spring.exception.PasswordNotEqualsException;
import com.user.spring.exception.UserAuthenticationException;
import com.user.spring.model.User;
import com.user.spring.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final AuthenticationManager manager;

    private final JwtService jwtService;

    private static String ROLES="ROLE_USER";

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder, AuthenticationManager manager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.manager = manager;
        this.jwtService = jwtService;
    }



    public String registerUser(RegisterDto registerDto){
        checkPassword(registerDto);
        User user=User.builder().email(registerDto.getEmail()).firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName()).roles(ROLES).password(encoder.encode(registerDto.getPassword()))
                .isActive(true).build();
        userRepository.save(user);
        return "Registered successfully!";
    }


    private void checkPassword(RegisterDto dto){
        if (!dto.getPassword().equals(dto.getRePassword()))
            throw new PasswordNotEqualsException("Passwords must be equals!");
    }

    public String authenticateUser(LoginDto loginDto) {
        Authentication authentication=manager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword()));
        if (!authentication.isAuthenticated()){
            throw new UserAuthenticationException("Wrong Email or Password!");
        }
        return jwtService.generateToken(loginDto.getEmail());
    }
}
