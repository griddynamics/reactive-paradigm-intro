package com.griddynamics.techtalk.reactor;

import com.griddynamics.techtalk.reactor.subscription.mapper.OrderInfoMapper;
import com.griddynamics.techtalk.reactor.subscription.model.Email;
import com.griddynamics.techtalk.reactor.subscription.model.OrderInfo;
import com.griddynamics.techtalk.reactor.subscription.model.Sms;
import com.griddynamics.techtalk.reactor.subscription.observer.OrderInfoPublisher;
import com.griddynamics.techtalk.reactor.subscription.service.EmailService;
import com.griddynamics.techtalk.reactor.subscription.service.OrderInfoService;
import com.griddynamics.techtalk.reactor.subscription.service.SmsService;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.griddynamics.techtalk.reactor.util.Util.sleep;

public class P02_01_Subscription {

    /** Pattern Observer */
    @Test
    public void observer() {
        System.out.println("\nObserver pattern\n");

        final String orderId = "Order123";
        final OrderInfoService orderInfoService = new OrderInfoService();

        Stream<OrderInfo> orderInfoData = IntStream.range(0, 10).boxed()
                .peek(ignore -> sleep(1))
                .map(ignore -> orderId)
                .map(orderInfoService::getOrderInfo);

        OrderInfoPublisher publisher = new OrderInfoPublisher();

        final String emailAddress = "to@gmail.com";
        publisher.subscribe(orderInfo -> {
            Email email = OrderInfoMapper.toEmail(orderInfo, emailAddress);
            EmailService.sendEmail(email);
        });

        final String phone = "99999999";
        publisher.subscribe(orderInfo -> {
            Sms sms = OrderInfoMapper.toSms(orderInfo, phone);
            SmsService.sendSms(sms);
        });

        orderInfoData.forEach(publisher::notify);
    }

    /** Reactor Subscriber */
    @Test
    public void subscriber() {
        System.out.println("\nSubscriber (reactor implementation)\n");

        final String orderId = "Order123";
        final OrderInfoService orderInfoService = new OrderInfoService();

        Flux<OrderInfo> orderInfoData = Flux.range(0, 10)
                .delayElements(Duration.ofSeconds(1))
                .map(ignore -> orderId)
                .map(orderInfoService::getOrderInfo);

        final String emailAddress = "to@gmail.com";
        orderInfoData
                .map(orderInfo -> OrderInfoMapper.toEmail(orderInfo, emailAddress))
                .subscribe(EmailService::sendEmail);

        final String phone = "99999999";
        orderInfoData
                .map(orderInfo -> OrderInfoMapper.toSms(orderInfo, phone))
                .subscribe(SmsService::sendSms);

        sleep(11);
    }

}
