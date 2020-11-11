package com.griddynamics.techtalk.reactor;

import org.junit.Test;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

import static com.griddynamics.techtalk.reactor.util.Util.sleep;

public class P03_Memorization {

    /** Memorization */
    @Test
    public void memorization() {
        Mono<String> source = Mono.fromCallable(() -> {
            System.out.println("code execution");
            return UUID.randomUUID().toString().substring(0, 8);
        }).cache(Duration.ofMillis(500));

        source.subscribe(System.out::println);
        source.subscribe(System.out::println);

        sleep(1);

        source.subscribe(System.out::println);
        source.subscribe(System.out::println);
    }
}
