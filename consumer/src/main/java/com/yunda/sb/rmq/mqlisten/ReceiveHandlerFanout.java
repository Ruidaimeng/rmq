package com.yunda.sb.rmq.mqlisten;/*
 * Description
 *@author Ruimeng
 *@Date 2018/12/11 16:13
 */
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiveHandlerFanout {

      @RabbitListener(queues = "queue01")
      public void receive_queue01( String message){
          System.out.println(message);
    }
    @RabbitListener(queues = "queue02")
    public void receive_queue02( String message){
        System.out.println(message);
    }
    @RabbitListener(queues = "queue03")
    public void receive_queue03( String message){
        System.out.println(message);
    }

}
