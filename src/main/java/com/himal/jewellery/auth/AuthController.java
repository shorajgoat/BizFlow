package com.himal.jewellery.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.himal.jewellery.user.UserService;
import com.himal.jewellery.user.LoginRequestDto;
import com.himal.jewellery.user.User;
import com.himal.jewellery.user.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
@Autowired
private  UserService userService;
@Autowired
private UserRepository userRepo;
@Autowired
private PasswordEncoder encoder;

@PostMapping("/login")
public ResponseEntity<String> login(@RequestBody LoginRequestDto dto) {

    Optional<User> userOptional = userRepo.findByUsername(dto.getUsername()); 

    if (userOptional.isEmpty() || !encoder.matches(dto.getPassword(), userOptional.get().getPassword())) {
        return ResponseEntity.status(401).body("Invalid username or password");
    }

    return ResponseEntity.ok("Login successful. Welcome " + userOptional.get().getFullname());
}
}
