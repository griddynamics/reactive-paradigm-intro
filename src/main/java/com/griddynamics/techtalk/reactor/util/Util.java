package com.griddynamics.techtalk.reactor.util;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class Util {
    @SneakyThrows
    public static void sleep(int seconds) {
        TimeUnit.SECONDS.sleep(seconds);
    }
}
