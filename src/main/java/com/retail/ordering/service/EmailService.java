package com.retail.ordering.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class EmailService {

    @Value("${spring.mail.mock:true}")
    private boolean mockMode;

    /**
     * Sends order confirmation email.
     * In mock mode, logs the email instead of sending it.
     */
    public void sendOrderConfirmation(String toEmail, String customerName, Long orderId, BigDecimal totalAmount) {
        String subject = "Order Confirmation - Order #" + orderId;
        String body = buildOrderConfirmationBody(customerName, orderId, totalAmount);

        if (mockMode) {
            log.info("=== MOCK EMAIL ===");
            log.info("To: {}", toEmail);
            log.info("Subject: {}", subject);
            log.info("Body:\n{}", body);
            log.info("=== END MOCK EMAIL ===");
        } else {
            // Real email sending — configure SMTP in application.properties
            log.info("Sending order confirmation email to: {}", toEmail);
            // mailSender.send(buildMimeMessage(toEmail, subject, body));
        }
    }

    public void sendWelcomeEmail(String toEmail, String customerName) {
        String subject = "Welcome to Retail Ordering - " + customerName + "!";
        String body = "Dear " + customerName + ",\n\n"
                + "Welcome to Retail Ordering! We're excited to have you on board.\n"
                + "Explore our delicious Pizzas, Drinks, and Breads.\n\n"
                + "Happy Ordering!\n"
                + "The Retail Ordering Team";

        if (mockMode) {
            log.info("=== MOCK WELCOME EMAIL ===");
            log.info("To: {}", toEmail);
            log.info("Subject: {}", subject);
            log.info("Body:\n{}", body);
            log.info("=== END MOCK WELCOME EMAIL ===");
        }
    }

    private String buildOrderConfirmationBody(String customerName, Long orderId, BigDecimal totalAmount) {
        return "Dear " + customerName + ",\n\n"
                + "Thank you for your order!\n\n"
                + "Order Details:\n"
                + "  Order ID: #" + orderId + "\n"
                + "  Total Amount: $" + totalAmount + "\n"
                + "  Status: CONFIRMED\n\n"
                + "Your order is being prepared and will be delivered soon.\n\n"
                + "Thank you for shopping with us!\n\n"
                + "Best regards,\n"
                + "The Retail Ordering Team";
    }
}
