package dev.employeemanagementsystem.Employeemanagementandattendance.tracker.controller;

import dev.employeemanagementsystem.Employeemanagementandattendance.tracker.service.NotificationService;
import org.springframework.web.bind.annotation.GetMapping;

public class TestNotificationController {
    private final NotificationService notificationService;

    public TestNotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/email")
    public String testEmail() {
        notificationService.sendEmail("recipient@example.com", "Test Email", "Hello, this is a test email!");
        return "Email sent!";
    }

    @GetMapping("/sms")
    public String testSms() {
        notificationService.sendSms("+911234567890", "Hello, this is a test SMS!");
        return "SMS sent!";
    }
}
