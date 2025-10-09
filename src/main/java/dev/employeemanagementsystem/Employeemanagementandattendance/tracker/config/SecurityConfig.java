package dev.employeemanagementsystem.Employeemanagementandattendance.tracker.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())       // disable CSRF for simplicity
                .cors(Customizer.withDefaults())    // enable CORS using CorsConfig
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll());
        return http.build();
    }
}
