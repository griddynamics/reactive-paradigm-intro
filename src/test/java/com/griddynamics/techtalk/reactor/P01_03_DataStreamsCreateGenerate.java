package com.griddynamics.techtalk.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;


public class P01_03_DataStreamsCreateGenerate {
    /*
     * Generate
     * */
    @Test
    public void rangeStream() {
        IntStream range = IntStream.range(5, 8);
        range.forEach(System.out::println);
    }

    @Test
    public void rangePublisher() {
        Flux<Integer> range = Flux.range(5, 3);
        range.subscribe(System.out::println);
    }
}
