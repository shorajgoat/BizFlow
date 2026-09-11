package com.himal.jewellery.auth;

import com.himal.jewellery.exception.ApiResponse;
import com.himal.jewellery.exception.DuplicateUsernameException;
import com.himal.jewellery.exception.InvalidCredentialsException;
import com.himal.jewellery.security.JwtUtil;
import com.himal.jewellery.user.LoginRequestDto;
import com.himal.jewellery.user.RegisterRequestDto;
import com.himal.jewellery.user.User;
import com.himal.jewellery.user.UserRepository;
import com.himal.jewellery.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginRequestDto dto) {

        User user = userRepo.findByUsername(dto.getUsername())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid username or password"));

        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        ApiResponse<String> response = new ApiResponse<>(HttpStatus.OK.value(), "Login successful", token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(@Valid @RequestBody RegisterRequestDto dto) {

        if (userRepo.findByUsername(dto.getUsername()).isPresent()) {
            throw new DuplicateUsernameException("Username already taken");
        }

        User newUser = new User(dto.getFullName(), dto.getUsername(), dto.getPassword(), dto.getRole());
        userService.registerUser(newUser);

        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.OK.value(), "User registered successfully", null);
        return ResponseEntity.ok(response);
    }
}