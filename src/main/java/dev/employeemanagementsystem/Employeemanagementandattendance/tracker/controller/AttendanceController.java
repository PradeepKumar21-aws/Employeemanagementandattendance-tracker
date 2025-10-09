package dev.employeemanagementsystem.Employeemanagementandattendance.tracker.controller;

import dev.employeemanagementsystem.Employeemanagementandattendance.tracker.model.Attendance;
import dev.employeemanagementsystem.Employeemanagementandattendance.tracker.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/mark/{employeeId}")
    public ResponseEntity<?> markAttendance(
            @PathVariable String employeeId,
            @RequestBody Attendance attendanceRequest) {

        try {
            Attendance attendance = attendanceService.markAttendance(employeeId, attendanceRequest.getStatus());
            return ResponseEntity.ok(attendance);
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<?> getEmployeeAttendance(
            @PathVariable String employeeId,
            @RequestParam String start,
            @RequestParam String end) {

        try {
            LocalDateTime startDate = LocalDateTime.parse(start);
            LocalDateTime endDate = LocalDateTime.parse(end);

            List<Attendance> attendanceList =
                    attendanceService.getAttendanceForEmployee(employeeId, startDate, endDate);

            return ResponseEntity.ok(attendanceList);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Invalid date format");
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
