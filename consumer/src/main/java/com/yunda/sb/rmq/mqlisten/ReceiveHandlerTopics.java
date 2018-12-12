package com.yunda.sb.rmq.mqlisten;/*
 * Description
 *@author Ruimeng
 *@Date 2018/12/12 11:08
 */

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiveHandlerTopics {
    @RabbitListener(queues = "topicA")//监听队列AA
    public void receive_queueAA( String message){
        System.out.println("Receiver: topicA ,我获取到消息："+message);
    }
    @RabbitListener(queues = "topicB") //监听队列BB
    public void receive_queueBB( String message){
        System.out.println("Receiver: topicB,我获取到消息："+message);
    }
}
