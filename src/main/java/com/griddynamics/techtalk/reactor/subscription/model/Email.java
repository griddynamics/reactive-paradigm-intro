package com.griddynamics.techtalk.reactor.subscription.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Email {
    private String from;
    private String to;
    private String title;
    private String text;
}
