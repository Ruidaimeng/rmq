package com.yunda.sb.rmq.mqlisten;/*
 * Description
 *@author Ruimeng
 *@Date 2018/12/10 13:18
 */

import com.rabbitmq.client.AMQP;
import com.yunda.sb.rmq.config.RabbitmqConfig;
import com.yunda.sb.rmq.domain.User;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
//@RabbitListener(queues = "hello")
public class ReceiveHandler {

//    @RabbitHandler
//    public void process(String hello) {
//        System.out.println("Receiver  : " + hello);
//    }
    @RabbitListener(queues = "hello")
    public void process(String hello) {
        System.out.println("Receiver  : " + hello);
    }


    //监听email队列
   /* @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_EMAIL})    //方法或者，类上注明
    public void receive_email( Message message){
        System.out.println(new String(message.getBody()));
    }*/
    //监听sms队列
    @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_SMS})
    public void receive_sms(Message message){
        System.out.println(message);
    }

    //监听sms队列
    @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_SMS})
    public void receive_sms(User user){
        System.out.println(user.toString());
    }

     @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_EMAIL})    //方法或者，类上注明
    public void receive_email( User user){
         System.out.println(user.toString());
    }


}
