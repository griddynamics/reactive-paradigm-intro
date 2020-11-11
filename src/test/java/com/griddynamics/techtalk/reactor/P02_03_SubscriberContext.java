package com.griddynamics.techtalk.reactor;

import org.junit.Test;
import reactor.core.publisher.Mono;

public class P02_03_SubscriberContext {

    /** Subscriber Context */
    @Test
    public void subscriberContext() {
        Mono<String> mono = Mono.just("init value")
                .doOnNext(System.out::println)
                .flatMap(value -> Mono.subscriberContext()
                        .map(ctx -> ctx.getOrDefault("key", "default value")))
                .doOnNext(System.out::println);

        System.out.println("\nsubscriber 1");
        mono.subscribe();

        System.out.println("\nsubscriber 2");
        mono.subscriberContext(ctx -> ctx.put("key", "context value"))
                .subscribe();
    }

}
