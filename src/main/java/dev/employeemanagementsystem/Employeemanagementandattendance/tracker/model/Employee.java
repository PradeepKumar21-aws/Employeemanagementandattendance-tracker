package dev.employeemanagementsystem.Employeemanagementandattendance.tracker.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String employeeId;

    private String name;
    private String department;
    private String role;
    private String email;
    private String phone;
}