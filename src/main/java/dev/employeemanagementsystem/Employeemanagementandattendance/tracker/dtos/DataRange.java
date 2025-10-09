package dev.employeemanagementsystem.Employeemanagementandattendance.tracker.dtos;
import lombok.Data;

@Data
public class DataRange {
    private String start; // ISO 8601 e.g., 2025-08-01T00:00:00
    private String end;   // ISO 8601 e.g., 2025-08-31T23:59:59
}
