package com.amqp.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    public static final String QUEUE1 = "hasan_queue";
    public static final String ROUTING_KEY1 = "hasan_routingKey";
    public static final String QUEUE2 = "prakash_queue";
    public static final String ROUTING_KEY2 = "prakash_routingKey";
    public static final String EXCHANGE = "the_exchange";

    @Bean
    public Queue queue1() {
        return new Queue(QUEUE1);
    }
    @Bean
    public Queue queue2() {
        // Queue tqueue = new Queue(QUEUE2);
        QueueBuilder queueBuilder = QueueBuilder.durable(QUEUE2);
        queueBuilder.maxLength(4);
        // queueBuilder.ttl(100000); // time to live for message in queue
        // queueBuilder.lazy(); // messages will be stored in disk
        // queueBuilder.deadLetterExchange(EXCHANGE); // message will be sent by producer again(automatically) if message was lost for some reasons
        // queueBuilder.deadLetterRoutingKey(EXCHANGE); // uses new routing key to access other queue for next try
        // queueBuilder.expires(20000) // queue expire time
        // queueBuilder.autoDelete();
        return queueBuilder.build();
    }

    // public Queue queue3() {
    //     // Queue tqueue = new Queue(QUEUE2);
    //     QueueBuilder queueBuilder = QueueBuilder.durable(QUEUE2);
    //     queueBuilder.maxLength(4);
    //     // set expire time
    //     return queueBuilder.build();
    // }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding1(@Qualifier("queue1") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY1);
    }
    @Bean
    public Binding binding2(@Qualifier("queue2") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY2);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
