package com.griddynamics.techtalk.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;

import static com.griddynamics.techtalk.reactor.util.Util.sleep;
import static java.time.Duration.ofMillis;

public class P01_05_DataStreamModificationFlatMap {
    /*
     * FlatMap example
     * */
    @Test
    public void flatMapStream() {
        IntStream.range(5, 8)
                .flatMap(i -> IntStream.of(i * 10, 2, 3))
                .forEach(i -> System.out.printf("i=%s%n", i));
    }

    @Test
    public void flatMapPublisher() {
        Flux.range(5, 3)
                .flatMap(item -> Flux.just(item * 10, "two", "three").delayElements(ofMillis(1)))
                .subscribe(i -> System.out.printf("i=%s%n", i));

        sleep(2);
    }

}
