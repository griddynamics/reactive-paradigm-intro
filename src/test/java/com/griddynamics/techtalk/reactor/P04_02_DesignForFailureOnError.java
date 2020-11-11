package com.griddynamics.techtalk.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;


public class P04_02_DesignForFailureOnError {

    private int checkNumber(int value) {
        if (value == 5) {
            throw new RuntimeException("Booom!!!!");
        }
        return value;
    }

    /*
     * onErrorReturn, onErrorResume example
     * */
    @Test
    public void onErrorReturnTest() {
        Flux.range(1, 10)
                .map(this::checkNumber)
                .onErrorReturn(20)
                .subscribe(i -> System.out.printf("i=%s%n", i));
    }

    @Test
    public void onErrorResumeTest() {
        Flux.range(1, 10)
                .map(this::checkNumber)
                .onErrorResume(i -> cachedNumber())
                .subscribe(i -> System.out.printf("i=%s%n", i));
    }

    private Flux<Integer> cachedNumber() {
        return Flux.range(5, 6);
    }
}
