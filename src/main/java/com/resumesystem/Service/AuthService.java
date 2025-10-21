package com.resumesystem.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.resumesystem.DTO.AuthResponse;
import com.resumesystem.DTO.LoginRequest;
import com.resumesystem.DTO.RegisterRequest;
import com.resumesystem.Entity.User;
import com.resumesystem.Enum.Role;
import com.resumesystem.Repository.UserRepository;
import com.resumesystem.Security.JwtService;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    // ✅ Register new user
    public void register(RegisterRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }

        if (req.getRole() == null) {
            throw new IllegalArgumentException("Role must be provided");
        }

        User user = User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .role(req.getRole())
                .build();

        userRepository.save(user);
    }

    // ✅ Login user and generate JWT
    public AuthResponse login(LoginRequest req) {
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        // Generate JWT using email
        String token = jwtService.generateToken(user.getEmail());
        System.out.println("Generated token for " + user.getEmail() + ": " + token);

        return new AuthResponse(token);
    }
}
