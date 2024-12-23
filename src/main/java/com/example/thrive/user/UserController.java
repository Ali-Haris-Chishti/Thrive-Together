package com.example.thrive.user;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserModel userModel) {
        return userService.registerUser(userModel);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(
            @RequestParam String username,
            @RequestParam String password
    ) {
        return userService.verifyLogin(username, password);
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateJwtToken(HttpServletRequest request){
        return userService.validateJwtToken(request.getHeader(HttpHeaders.AUTHORIZATION).substring(7));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/get-all")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.ok(null);
    }
}
