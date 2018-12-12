package com.yunda.sb.rmq.mqlisten;/*
 * Description
 *@author Ruimeng
 *@Date 2018/12/10 16:50
 */

import org.springframework.amqp.rabbit.annotation.*;

import java.io.UnsupportedEncodingException;

/*@Component
@RabbitListener(bindings = {@QueueBinding
        (value = @Queue(value = "roberto.order.add",
        durable = "true", autoDelete = "false", exclusive = "false"),
        exchange = @Exchange(name = "roberto.order"))})*/
public class SpringAMQPMessageHandle {
    @RabbitHandler
    public void add(byte[] body) {
        System.out.println("----------byte[]方法进行处理----------");
        try {
            System.out.println(new String(body, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
