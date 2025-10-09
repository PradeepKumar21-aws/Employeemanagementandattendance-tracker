package dev.employeemanagementsystem.Employeemanagementandattendance.tracker.Admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    // Hardcoded admin credentials for testing
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "1234";

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("password");

        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            return ResponseEntity.ok(Map.of("message", "Login successful"));
        } else {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid username or password"));
        }
    }
}

