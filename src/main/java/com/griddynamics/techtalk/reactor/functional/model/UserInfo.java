package com.griddynamics.techtalk.reactor.functional.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfo {
    private String name;
    private String surname;
    private String role;
}
