package com.griddynamics.techtalk.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;


public class P01_06_DataStreamModificationFilter {

    /*
     * Filter example
     * */
    @Test
    public void filterStream() {
        IntStream.range(5, 16)
                .filter(item -> item > 10)
                .forEach(i -> System.out.printf("i=%s%n", i));
    }

    @Test
    public void filterPublisher() {
        Flux.range(5, 10)
                .filter(item -> item > 10)
                .subscribe(i -> System.out.printf("i=%s%n", i));
    }
}
