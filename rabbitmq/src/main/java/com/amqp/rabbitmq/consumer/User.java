package com.amqp.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.amqp.rabbitmq.config.MessagingConfig;
import com.amqp.rabbitmq.dto.OrderStatus;

@Component
public class User {

    @RabbitListener(queues = MessagingConfig.QUEUE1)
    public void consumeMessageFromQueue1(OrderStatus orderStatus) {
        System.out.println("Message recieved from queue with name \'HASAN' : " + orderStatus);
    }

    // @RabbitListener(queues = MessagingConfig.QUEUE2)
    // public void consumeMessageFromQueue2(OrderStatus orderStatus) {
    //     System.out.println("Message recieved from queue with name \'PRAKASH' : " + orderStatus);
    // }
}