package com.griddynamics.techtalk.reactor.subscription.observer;

public interface Publisher<T> {
    void subscribe(Subscriber<T> subscriber);

    void notify(T t);

}
