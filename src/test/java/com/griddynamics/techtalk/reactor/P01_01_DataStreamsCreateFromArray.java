package com.griddynamics.techtalk.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.stream.Stream;


public class P01_01_DataStreamsCreateFromArray {
    /*
     * Create from array
     * */
    @Test
    public void forTest() {
        for (int i = 0; i < 10; i++) {
            System.out.println("i=" + i);
        }
    }

    @Test
    public void arrayStream() {
        Stream<String> str = Stream.of("a", "b", "c");
        str.forEach(System.out::println);
    }

    @Test
    public void arrayPublisher() {
        Flux<String> str = Flux.just("a", "b", "c");
        str.subscribe(System.out::println);
    }

}
