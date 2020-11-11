package com.griddynamics.techtalk.reactor.functional;

import com.griddynamics.techtalk.reactor.functional.service.PassportService;
import com.griddynamics.techtalk.reactor.functional.service.UserInfoService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

import static com.griddynamics.techtalk.reactor.functional.service.UserInfoService.ADMIN_ROLE;
import static com.griddynamics.techtalk.reactor.util.Util.sleep;

public class FunctionalRunner {

    private static void declarative() {
        Mono<String> passportNumber = UserInfoService.getUserInfo()
                .filter(user -> user.getRole().equals(ADMIN_ROLE))
                .map(user -> user.getName() + " " + user.getSurname())
                .flatMap(PassportService::getPassportNumberByFullName)
                .switchIfEmpty(Mono.error(new RuntimeException("Not found")))
                .log();

        passportNumber.block();
    }

    private static void pureFunctions() {
        Flux.range(0, 10)
                .map(x -> x * 2) // pure function
                .doOnNext(System.out::println) // mitigation of side effects
                .blockLast();
    }

    private static void lazyEvaluation() {
        System.out.println("before pipeline creation");
        Mono<String> mono = Mono.just("start")
                .doOnNext(System.out::println)
                .map(x -> "map")
                .doOnNext(System.out::println)
                .map(x -> "finish")
                .doOnNext(System.out::println);

        System.out.println("after pipeline creation");

        System.out.println("before subscribing");

        mono.subscribe();

        System.out.println("after subscribing");
    }

    private static void memorization() {
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
