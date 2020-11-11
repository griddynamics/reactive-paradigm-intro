package com.griddynamics.techtalk.reactor.subscription.mapper;

import com.griddynamics.techtalk.reactor.subscription.model.Email;
import com.griddynamics.techtalk.reactor.subscription.model.OrderInfo;
import com.griddynamics.techtalk.reactor.subscription.model.Sms;

public class OrderInfoMapper {
    private static final String TEXT_TEMPLATE = "Your order (%s) has current status: %s";
    private static final String FROM = "from@gmail.com";
    private static final String TITLE = "Order status";

    public static Sms toSms(OrderInfo orderInfo, String phone) {
        String text = String.format(TEXT_TEMPLATE, orderInfo.getOrderId(), orderInfo.getStatus());
        return Sms.builder()
                .phone(phone)
                .text(text)
                .build();
    }

    public static Email toEmail(OrderInfo orderInfo, String email) {
        String text = String.format(TEXT_TEMPLATE, orderInfo.getOrderId(), orderInfo.getStatus());
        return Email.builder()
                .from(FROM)
                .to(email)
                .title(TITLE)
                .text(text)
                .build();
    }
}
