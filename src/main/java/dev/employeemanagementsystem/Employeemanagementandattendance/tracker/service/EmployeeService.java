package dev.employeemanagementsystem.Employeemanagementandattendance.tracker.service;

import dev.employeemanagementsystem.Employeemanagementandattendance.tracker.exception.ResourceNotFoundException;
import dev.employeemanagementsystem.Employeemanagementandattendance.tracker.model.Employee;
import dev.employeemanagementsystem.Employeemanagementandattendance.tracker.repository.EmployeeRepository;
import dev.employeemanagementsystem.Employeemanagementandattendance.tracker.service.NotificationService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service

public class EmployeeService {

    private final EmployeeRepository repo;
    private final NotificationService notificationService;

    public EmployeeService(EmployeeRepository repo, NotificationService notificationService) {
        this.repo = repo;
        this.notificationService = notificationService;
    }

    //  List employees with pagination
    public Page<Employee> list(int page, int size) {
        return repo.findAll(PageRequest.of(page, size));
    }

    //  Get employee by ID
    public Employee get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    // Create new employee + send welcome notification
    public Employee create(Employee employee) {
        if (repo.existsByEmployeeId(employee.getEmployeeId())) {
            throw new IllegalArgumentException("Employee with ID " + employee.getEmployeeId() + " already exists");
        }

        Employee saved = repo.save(employee);

        //  Notification
        if (saved.getEmail() != null) {
            notificationService.sendEmail(
                    saved.getEmail(),
                    "Welcome to Company",
                    "Hello " + saved.getName() + ", your employee profile has been created successfully."
            );
        }

        if (saved.getPhone() != null) {
            notificationService.sendSms(
                    saved.getPhone(),
                    "Welcome " + saved.getName() + "! Your employee profile has been created."
            );
        }

        return saved;
    }

    //  Update employee + notify
    public Employee update(Long id, Employee updatedEmployee) {
        Employee existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        existing.setName(updatedEmployee.getName());
        existing.setEmployeeId(updatedEmployee.getEmployeeId());
        existing.setDepartment(updatedEmployee.getDepartment());
        existing.setRole(updatedEmployee.getRole());
        existing.setEmail(updatedEmployee.getEmail());
        existing.setPhone(updatedEmployee.getPhone());

        Employee saved = repo.save(existing);

        // 📧 + 📱 Notification
        if (saved.getEmail() != null) {
            notificationService.sendEmail(
                    saved.getEmail(),
                    "Profile Updated",
                    "Hi " + saved.getName() + ", your employee profile has been updated successfully."
            );
        }

        if (saved.getPhone() != null) {
            notificationService.sendSms(
                    saved.getPhone(),
                    "Hi " + saved.getName() + ", your profile has been updated."
            );
        }

        return saved;
    }

    // ✅ Delete employee + notify
    public void delete(Long id) {
        Employee existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        repo.deleteById(id);

        // 📧 + 📱 Notification
        if (existing.getEmail() != null) {
            notificationService.sendEmail(
                    existing.getEmail(),
                    "Profile Deleted",
                    "Hi " + existing.getName() + ", your employee profile has been removed from our system."
            );
        }

        if (existing.getPhone() != null) {
            notificationService.sendSms(
                    existing.getPhone(),
                    "Hi " + existing.getName() + ", your profile has been deleted."
            );
        }
    }

    // ✅ Get employee by employeeId (for Attendance use)
    public Employee getByEmployeeId(@NotBlank String employeeId) {
        return repo.findByEmployeeId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with employeeId: " + employeeId));
    }
}
