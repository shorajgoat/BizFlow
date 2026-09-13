package com.himal.jewellery.auth;

import com.himal.jewellery.exception.ApiResponse;
import com.himal.jewellery.exception.DuplicateUsernameException;
import com.himal.jewellery.exception.InvalidCredentialsException;
import com.himal.jewellery.exception.InvalidTokenException;
import com.himal.jewellery.security.JwtUtil;
import com.himal.jewellery.user.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<ApiResponse<Map<String, String>>> login(@RequestBody LoginRequestDto dto) {

        User user = userRepo.findByUsername(dto.getUsername())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid username or password"));

        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        String accessToken = jwtUtil.generateToken(user.getUsername(), user.getRole());
        String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        ApiResponse<Map<String, String>> response =
                new ApiResponse<>(HttpStatus.OK.value(), "Login successful", tokens);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<String>> refresh(@RequestBody RefreshRequestDto dto) {

        String token = dto.getRefreshToken();

        if (!jwtUtil.validateToken(token)) {
            throw new InvalidTokenException("Invalid or expired refresh token" );
        }

        if (!"refresh".equals(jwtUtil.extractTokenType(token))) {
            throw new InvalidTokenException("Provided token is not a refresh token");
        }

        String username = jwtUtil.extractUsername(token);

        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new InvalidCredentialsException("User no longer exists"));

        String newAccessToken = jwtUtil.generateToken(user.getUsername(), user.getRole());

        ApiResponse<String> response =
                new ApiResponse<>(HttpStatus.OK.value(), "Token refreshed", newAccessToken);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(@Valid @RequestBody RegisterRequestDto dto) {

        if (userRepo.findByUsername(dto.getUsername()).isPresent()) {
            throw new DuplicateUsernameException("Username already taken");
        }

        User newUser = new User(dto.getFullName(), dto.getUsername(), dto.getPassword(), dto.getRole());
        userService.registerUser(newUser);

        ApiResponse<Void> response =
                new ApiResponse<>(HttpStatus.OK.value(), "User registered successfully", null);
        return ResponseEntity.ok(response);
    }
}