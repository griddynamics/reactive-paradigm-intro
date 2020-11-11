package com.griddynamics.techtalk.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class P02_02_PullPush {

    /** Pull-based stream */
    @Test
    public void pull() {
        Stream<Integer> stream = IntStream.range(0, 10).boxed()
                .peek(x -> System.out.println("init: " + x))
                .map(x -> x * 2);

        System.out.println("\nterminator 1");
        int sum = stream.reduce(Integer::sum).orElse(0);
        System.out.println("sum: " + sum);

        System.out.println("\nterminator 2");
        stream.forEach(x -> System.out.println("result: " + x));
    }

    /** Push-based stream */
    @Test
    public void push() {
        Flux<Integer> flux = Flux.range(0, 10)
                .doOnNext(x -> System.out.println("init: " + x))
                .map(x -> x * 2);

        System.out.println("\nsubscriber 1");
        flux.reduce(Integer::sum)
                .subscribe(sum -> System.out.println("sum: " + sum));

        System.out.println("\nsubscriber 2");
        flux.subscribe(x -> System.out.println("result: " + x));
    }

}
