package com.griddynamics.techtalk.reactor.subscription.service;

import com.griddynamics.techtalk.reactor.subscription.model.Sms;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SmsService {
    public static void sendSms(Sms sms) {
        log.info("Send sms: " + sms);
    }
}
