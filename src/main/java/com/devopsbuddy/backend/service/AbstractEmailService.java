package com.devopsbuddy.backend.service;

import com.devopsbuddy.web.domain.frontend.FeedbackPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.to.address}")
    private String defaultToAddress;

    /**
     * Creates a simple mail message from a feedback pojo.
     * @param feedback The feedback pojo
     * @return
     */
    private SimpleMailMessage  prepareSimpleMailMessageFromFeedbackPojo(FeedbackPojo feedback) {
        SimpleMailMessage messsage = new SimpleMailMessage();
        messsage.setTo(defaultToAddress);
        messsage.setFrom(feedback.getEmail());
        messsage.setSubject("[DevOps buddy]: Feedback received from " + feedback.getFirstName() + " " + feedback
            .getLastName() + "!" );
        messsage.setText(feedback.getFeedback());
        return messsage;
    }

    @Override
    public void sendFeedbackEmail(FeedbackPojo feedbackPojo) {
        sendGenericEmailMessage(prepareSimpleMailMessageFromFeedbackPojo(feedbackPojo));
    }
}
