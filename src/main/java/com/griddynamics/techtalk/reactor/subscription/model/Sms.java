package com.griddynamics.techtalk.reactor.subscription.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Sms {
    private String phone;
    private String text;
}
