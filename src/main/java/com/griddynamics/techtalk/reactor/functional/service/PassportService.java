package com.griddynamics.techtalk.reactor.functional.service;

import reactor.core.publisher.Mono;

import java.util.UUID;

public class PassportService {
    public static Mono<String> getPassportNumberByFullName(String fullName) {
        return Mono.just(UUID.randomUUID().toString());
    }
}
