package dev.employeemanagementsystem.Employeemanagementandattendance.tracker.repository;
import dev.employeemanagementsystem.Employeemanagementandattendance.tracker.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmployeeId(String employeeId);
    boolean existsByEmployeeId(String employeeId);

}
