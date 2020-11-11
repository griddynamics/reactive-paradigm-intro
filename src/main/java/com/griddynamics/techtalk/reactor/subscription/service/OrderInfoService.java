package com.griddynamics.techtalk.reactor.subscription.service;

import com.griddynamics.techtalk.reactor.subscription.model.OrderInfo;
import lombok.Builder;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OrderInfoService {
    private static final int INTERVAL_FOR_CHANGE_STATUS = 2;

    private static List<String> statuses = List.of("Created", "Shipped", "Delivered");

    private Map<String, CurrentState> orderStates = new ConcurrentHashMap<>();

    public OrderInfo getOrderInfo(String orderId) {
        int index = computeIndex(orderId);
        String status = statuses.get(index);
        return OrderInfo.builder()
                .orderId(orderId)
                .status(status)
                .build();
    }

    private Integer computeIndex(String orderId) {
        CurrentState state = orderStates.computeIfAbsent(orderId, id -> initialState());
        int index = state.index;
        if (index < statuses.size() - 1 && checkTime(state)) {
            orderStates.put(orderId, toState(index + 1));
        }
        return index;
    }

    private static CurrentState initialState() {
        return toState(0);
    }

    private static boolean checkTime(CurrentState state) {
        long current = Instant.now().getEpochSecond();
        return (current - state.time) > INTERVAL_FOR_CHANGE_STATUS;
    }

    private static CurrentState toState(int index) {
        long time = Instant.now().getEpochSecond();
        return CurrentState.builder()
                .index(index)
                .time(time)
                .build();
    }

    @Builder
    private static class CurrentState {
        private int index;
        private long time;
    }
}
