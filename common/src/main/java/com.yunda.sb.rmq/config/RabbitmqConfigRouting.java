package com.yunda.sb.rmq.config;/*
 * Description
 *@author Ruimeng
 *@Date 2018/12/12 9:19
 * 交换机为direct模式，队列绑定交换机，并指定路由键，
 * 生产者，发送消息到交换机，并同时给出了路由键，
 * 交换机根据给过来的路由键，将消息，放到相应得到队列中去。
 */

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqConfigRouting {

    /**第一步，创建交换机
     * 交换机配置
     * ExchangeBuilder提供了fanout、direct、topic、header交换机类型的配置
     */
    @Bean("routingExchange")
    public DirectExchange creatRoutingExchange() {
        //durable(true)持久化，消息队列重启后交换机仍然存在
        //注意在这里指定的交换机类型，是fanout
        return new DirectExchange("routingExchange");
    }
    /**第二步，创建队列
     * 指定队列名称，bean名称
     */

    //声明队列
    @Bean("queueAA")  //队列名称
    public Queue QUEUE_AA() {
        Queue queue = new Queue("queueAA");
        return queue;
    }

    //声明队列
    @Bean("queueBB")  //队列名称
    public Queue QUEUE_BB() {
        Queue queue = new Queue("queueBB");
        return queue;
    }

    /**
     * 第三步，队列绑定交换机，并指定绑定规则，即说明绑定的路由规则
     * 传入参数，交换机和队列
     */
    @Bean
    public Binding BINDING_QUEUE_AA(@Qualifier("queueAA") Queue queue,
                                            @Qualifier("routingExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("AA");
    }

    @Bean
    public Binding BINDING_QUEUE_BB(@Qualifier("queueBB") Queue queue,
                                            @Qualifier("routingExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("BB");
    }


}
