package dev.employeemanagementsystem.Employeemanagementandattendance.tracker.repository;

import dev.employeemanagementsystem.Employeemanagementandattendance.tracker.model.Attendance;
import dev.employeemanagementsystem.Employeemanagementandattendance.tracker.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByEmployeeAndTimestampBetween(Employee employee, LocalDateTime start, LocalDateTime end);
}
