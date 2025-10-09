package dev.employeemanagementsystem.Employeemanagementandattendance.tracker.service;

import dev.employeemanagementsystem.Employeemanagementandattendance.tracker.model.Attendance;
import dev.employeemanagementsystem.Employeemanagementandattendance.tracker.model.Employee;
import dev.employeemanagementsystem.Employeemanagementandattendance.tracker.repository.AttendanceRepository;
import dev.employeemanagementsystem.Employeemanagementandattendance.tracker.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    public Attendance markAttendance(String employeeId, Attendance.Status status) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + employeeId));

        Attendance attendance = Attendance.builder()
                .employee(employee)
                .timestamp(LocalDateTime.now())
                .status(status)
                .build();

        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAttendanceForEmployee(String employeeId, LocalDateTime start, LocalDateTime end) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + employeeId));

        return attendanceRepository.findByEmployeeAndTimestampBetween(employee, start, end);
    }
}
