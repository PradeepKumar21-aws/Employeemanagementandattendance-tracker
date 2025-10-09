package dev.employeemanagementsystem.Employeemanagementandattendance.tracker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ Changed to EAGER and added @JsonIgnoreProperties to fix serialization
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Employee employee;

    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        PRESENT, ABSENT, LATE, ON_LEAVE
    }
}
