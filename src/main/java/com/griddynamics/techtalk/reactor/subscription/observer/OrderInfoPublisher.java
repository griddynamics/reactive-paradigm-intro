package com.griddynamics.techtalk.reactor.subscription.observer;

import com.griddynamics.techtalk.reactor.subscription.model.OrderInfo;

import java.util.ArrayList;
import java.util.List;

public class OrderInfoPublisher implements Publisher<OrderInfo> {
    private List<Subscriber<OrderInfo>> subscribers = new ArrayList<>();

    @Override
    public void subscribe(Subscriber<OrderInfo> subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void notify(OrderInfo orderInfo) {
        subscribers.forEach(subscriber -> subscriber.onNext(orderInfo));
    }
}
