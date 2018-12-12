package com.yunda.sb.rmq.mqlisten;/*
 * Description
 *@author Ruimeng
 *@Date 2018/12/12 9:49
 */

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiveHandlerRouting {

    @RabbitListener(queues = "queueAA")//监听队列AA
    public void receive_queueAA( String message){
        System.out.println("Receiver A "+message);
    }
    @RabbitListener(queues = "queueBB") //监听队列BB
    public void receive_queueBB( String message){
        System.out.println("Receiver B "+message);
    }
}
