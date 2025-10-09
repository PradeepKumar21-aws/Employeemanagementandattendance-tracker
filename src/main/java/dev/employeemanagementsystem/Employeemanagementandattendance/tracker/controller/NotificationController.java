package dev.employeemanagementsystem.Employeemanagementandattendance.tracker.controller;

import dev.employeemanagementsystem.Employeemanagementandattendance.tracker.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    //  Send email notification manually
    @PostMapping("/email")
    public ResponseEntity<String> sendEmail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String message
    ) {
        try {
            notificationService.sendEmail(to, subject, message);
            return ResponseEntity.ok("✅ Email sent successfully to " + to);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("❌ Failed to send email: " + e.getMessage());
        }
    }

    //  Send SMS notification manually
    @PostMapping("/sms")
    public ResponseEntity<String> sendSms(
            @RequestParam String phone,
            @RequestParam String message
    ) {
        try {
            notificationService.sendSms(phone, message);
            return ResponseEntity.ok("✅ SMS sent successfully to " + phone);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("❌ Failed to send SMS: " + e.getMessage());
        }
    }

    //  Health check endpoint for notification system
    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("📨 Notification service is running fine!");
    }
}
