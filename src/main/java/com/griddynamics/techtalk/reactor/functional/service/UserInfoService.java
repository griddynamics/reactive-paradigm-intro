package com.griddynamics.techtalk.reactor.functional.service;

import com.griddynamics.techtalk.reactor.functional.model.UserInfo;
import reactor.core.publisher.Mono;

public class UserInfoService {
    public static final String ADMIN_ROLE = "admin";

    public static Mono<UserInfo> getUserInfo() {
        UserInfo userInfo = UserInfo.builder()
                .name("Jack")
                .surname("Silver")
                .role(ADMIN_ROLE)
                .build();
        return Mono.just(userInfo);
    }
}
