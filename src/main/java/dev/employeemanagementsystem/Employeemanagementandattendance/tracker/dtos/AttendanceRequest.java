package dev.employeemanagementsystem.Employeemanagementandattendance.tracker.dtos;


import dev.employeemanagementsystem.Employeemanagementandattendance.tracker.model.Attendance; // fixed import
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AttendanceRequest {

    @NotBlank
    private String employeeId;

    private Attendance.Status status = Attendance.Status.PRESENT; // default value
}
