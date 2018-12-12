package com.yunda.sb.rmq.config;/*
 * Description
 *@author Ruimeng
 *@Date 2018/12/11 15:21
 * 发布订阅模式，声明交换机，声明（一般为多个）队列，队列绑定交换机
 * 交换机拿到消息，给每个队列都发送一条，
 *
 */

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfigFanout {
    //交换机名称
    public static final String EXCHANGE_TOPICS_INFORM="fanoutExchange";
    //队列名称
    public static final String QUEUE_INFORM_EMAIL = "queue01";
    public static final String QUEUE_INFORM_SMS = "queue02";
    /**
     * 交换机配置
     * ExchangeBuilder提供了fanout、direct、topic、header交换机类型的配置
     * @return the exchange
     */
    @Bean(EXCHANGE_TOPICS_INFORM)
    public FanoutExchange EXCHANGE_TOPICS_INFORM() {
        //durable(true)持久化，消息队列重启后交换机仍然存在
        //注意在这里指定的交换机类型，是fanout
        return new FanoutExchange(EXCHANGE_TOPICS_INFORM);
    }

    //声明队列
    @Bean(QUEUE_INFORM_SMS)
    public Queue QUEUE_INFORM_SMS() {
        Queue queue = new Queue(QUEUE_INFORM_SMS);
        return queue;
    }
    //声明队列
    @Bean(QUEUE_INFORM_EMAIL)
    public Queue QUEUE_INFORM_EMAIL() {
        Queue queue = new Queue(QUEUE_INFORM_EMAIL);
        return queue;
    }

    //声明队列
    @Bean("queue03")  //队列名称
    public Queue QUEUE_03() {
        Queue queue = new Queue("queue03");
        return queue;
    }

    /**
     * 绑定队列到交换机
     * @param queue the queue
     * @param exchange the exchange
     * @return the binding
     */
    @Bean
    public Binding bindQueue01(@Qualifier(QUEUE_INFORM_SMS) Queue queue,
                                            @Qualifier(EXCHANGE_TOPICS_INFORM) FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }
    @Bean
    public Binding bindQueue02(@Qualifier(QUEUE_INFORM_EMAIL) Queue queue,
                                              @Qualifier(EXCHANGE_TOPICS_INFORM) FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public Binding bindQueue03(@Qualifier("queue03") Queue queue,
                                              @Qualifier(EXCHANGE_TOPICS_INFORM) FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }




}
