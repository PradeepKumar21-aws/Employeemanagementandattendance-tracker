package dev.employeemanagementsystem.Employeemanagementandattendance.tracker.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}