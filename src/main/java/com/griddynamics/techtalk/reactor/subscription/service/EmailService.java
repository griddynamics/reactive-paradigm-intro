package com.griddynamics.techtalk.reactor.subscription.service;

import com.griddynamics.techtalk.reactor.subscription.model.Email;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailService {
    public static void sendEmail(Email email) {
        log.info("Send email: " + email);
    }
}
