package com.griddynamics.techtalk.reactor;


import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

import static com.griddynamics.techtalk.reactor.util.Util.sleep;

@Log4j2
public class P05_AsynchronousCall {

    /*
     * asynchronous work
     * */
    @Test
    public void asynchronousStream() {
        IntStream.range(1, 8)
                .parallel()
                .flatMap(i -> IntStream.of(i * 10, 2, 3))
                .forEach(i -> System.out.printf("i=%s%n", i));
    }

    @Test
    public void publishOnTest() {
        final Flux<String> flux = Flux
                .range(1, 2)
                .map(i -> {
                    log.info(String.format("First map, thread is:::%s, i=%s%n",Thread.currentThread().getName(),i));
                    return i * 10;
                })
                .publishOn(Schedulers.newSingle("@_2_Thread"))
                .map(i -> {
                    log.info(String.format("Second map, thread is:::%s, i=%s%n",Thread.currentThread().getName(),i));
                    return "Return value " + i;
                });

        flux.subscribe(log::info);

        sleep(2);
    }

    @Test
    public void subscribeOnTest() {
        final Flux<String> flux = Flux
                .range(1, 2)
                .map(i -> {
                    log.info(String.format("First map, thread is:::%s, i=%s%n",Thread.currentThread().getName(),i));
                    return i * 10;
                })
                .subscribeOn(Schedulers.newSingle("@_2_Thread"))
                .map(i -> {
                    log.info(String.format("Second map, thread is:::%s, i=%s%n",Thread.currentThread().getName(),i));
                    return "Return value " + i;
                });

        flux.subscribe(log::info);

        sleep(2);
    }
}
