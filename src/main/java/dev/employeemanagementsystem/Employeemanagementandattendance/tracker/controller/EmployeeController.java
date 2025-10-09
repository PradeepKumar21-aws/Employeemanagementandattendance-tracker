package dev.employeemanagementsystem.Employeemanagementandattendance.tracker.controller;

import dev.employeemanagementsystem.Employeemanagementandattendance.tracker.model.Employee;
import dev.employeemanagementsystem.Employeemanagementandattendance.tracker.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //  List all employees (with pagination)
    @GetMapping
    public ResponseEntity<Page<Employee>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(employeeService.list(page, size));
    }

    //  Get single employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> get(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.get(id));
    }

    //  Create new employee
    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.create(employee));
    }

    //  Update existing employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.update(id, employee));
    }

    //  Delete employee by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.ok("Employee deleted successfully.");
    }

    //  Find employee by employeeId
    @GetMapping("/find/{employeeId}")
    public ResponseEntity<Employee> findByEmployeeId(@PathVariable String employeeId) {
        return ResponseEntity.ok(employeeService.getByEmployeeId(employeeId));
    }
}
