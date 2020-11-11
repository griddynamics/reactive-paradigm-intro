package com.griddynamics.techtalk.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;


public class P01_07_DataStreamModificationReduce {
    /*
     * Reduce example
     * */
    @Test
    public void reduceStream() {
        IntStream.range(5, 8)
                .reduce(Integer::sum)
                .ifPresent(i -> System.out.printf("i=%s%n", i));

    }

    @Test
    public void reducePublisher() {
        Flux.range(5, 3)
                .reduce(Integer::sum)
                .subscribe(i -> System.out.printf("i=%s%n", i));
    }
}
