package dev.employeemanagementsystem.Employeemanagementandattendance.tracker.service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final JavaMailSender mailSender;

    @Value("${twilio.account-sid}")
    private String accountSid;

    @Value("${twilio.auth-token}")
    private String authToken;

    @Value("${twilio.phone-number}")
    private String fromPhone;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public NotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    //  Email Notification
    public void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
            System.out.println("✅ Email sent to " + to);
        } catch (Exception e) {
            System.err.println("❌ Failed to send email: " + e.getMessage());
        }
    }

    //  SMS Notification (Twilio)
    public void sendSms(String to, String body) {
        try {
            Twilio.init(accountSid, authToken);
            Message.creator(
                    new com.twilio.type.PhoneNumber(to),
                    new com.twilio.type.PhoneNumber(fromPhone),
                    body
            ).create();
            System.out.println("✅ SMS sent to " + to);
        } catch (Exception e) {
            System.err.println("❌ Failed to send SMS: " + e.getMessage());
        }
    }
}
