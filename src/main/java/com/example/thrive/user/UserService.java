package com.example.thrive.user;

import com.example.thrive.config.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public ResponseEntity<String> registerUser(UserModel userModel) {
        userModel.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));
        try {
            userModel = userRepository.save(userModel);
            return ResponseEntity.ok().body(jwtService.generateToken(userModel.getUsername()));
        }
        catch (Exception e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }


    public ResponseEntity<String> verifyLogin(String username, String password) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok().body(jwtService.generateToken(username));
        }
        return ResponseEntity.status(401).body("Invalid username or password");
    }

    public ResponseEntity<?> validateJwtToken(String jwtToken) {
        try {
            String username = jwtService.extractUsernameFromToken(jwtToken);
            Optional<UserModel> user = userRepository.findByUsername(username);
            if (user.isPresent())
                return ResponseEntity.ok().body(user.get());
            throw new IllegalArgumentException("Invalid token");
        }
        catch (Exception e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

}
