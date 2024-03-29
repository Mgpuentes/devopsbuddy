package com.devopsbuddy.backend.service;

import com.devopsbuddy.web.domain.frontend.FeedbackPojo;
import org.springframework.mail.SimpleMailMessage;

/**
 * Contract for email service.
 */
public interface EmailService {

    /**
     * Sends an email with the content in the feedback pojo.
     * @param feedbackPojo The feedback pojo
     */
    public void sendFeedbackEmail(FeedbackPojo feedbackPojo);

    /**
     * Sends an email with the content of the SimpleMailMessage object.
     * @param message The object containing the email content.
     */
    public void sendGenericEmailMessage(SimpleMailMessage message);
}
