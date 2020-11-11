package com.griddynamics.techtalk.reactor.subscription.observer;

@FunctionalInterface
public interface Subscriber<T> {
    void onNext(T data);
}
