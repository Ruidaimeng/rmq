package com.yunda.sb.rmq.config;/*
 * Description
 *@author Ruimeng
 *@Date 2018/12/12 10:14
 */

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 *  转发消息主要是根据通配符。 在这种交换机下，队列和交换机的绑定会定义一种路由模式，
 *  那么，通配符就要在这种路由模式和路由键之间匹配后交换机才能转发消息。
 *  路由键必须是一串字符，用句号（.） 隔开，比如说 agreements.us，或者 agreements.eu.stockholm 等。
 *  路由模式必须包含一个 星号（*），主要用于匹配路由键指定位置的一个单词，比如说，一个路由模式是这样子：
 *  agreements..b.*，那么就只能匹配路由键是这样子的：第一个单词是 agreements，第四个单词是 b。
 *  井号（#）就表示相当于一个或者多个单词，例如一个匹配模式是agreements.eu.berlin.#，
 *  那么，以agreements.eu.berlin开头的路由键都是可以的。
 * 具体代码发送的时候还是一样，第一个参数表示交换机，第二个参数表示routing key，第三个参数即消息。
 *      *表示一个词.
 *       #表示零个或多个词.
 *
 * topic 和 direct 类似, 只是匹配上支持了"模式", 在"点分"的 routing_key 形式中, 可以使用两个通配符:
 *
 * */
@Component
public class RabbitmqConfigTopics {
    /**第一步，创建交换机
     * 交换机配置
     * ExchangeBuilder提供了fanout、direct、topic、header交换机类型的配置
     */
    @Bean("topicsExchange")
    public TopicExchange createTopExchange() {
        //durable(true)持久化，消息队列重启后交换机仍然存在
        //注意在这里指定的交换机类型，是topic
        return new TopicExchange("topicsExchange");
    }
    /**第二步，创建队列
     * 指定队列名称，bean名称
     */
    @Bean("topicA")
    public Queue QueueA() {
        return new Queue("topicA");
    }

    @Bean("topicB")
    public Queue QueueB() {
        return new Queue("topicB");
    }
    /**
     * 第三步，队列绑定交换机，并指定绑定规则，
     * 绑定规则为指定路由模式，不是一个固定的路由键
     * 传入参数，交换机和队列
     */
    @Bean
    public Binding BINDING_QUEUE_Topic_A(@Qualifier("topicA") Queue queue,
                                            @Qualifier("topicsExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("topic.#.A.*");
    }
    @Bean
    public Binding BINDING_QUEUE_Topic_B(@Qualifier("topicB") Queue queue,
                                              @Qualifier("topicsExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("topic.*.B.#");
    }




}
