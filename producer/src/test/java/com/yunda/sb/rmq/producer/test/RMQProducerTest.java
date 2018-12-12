package com.yunda.sb.rmq.producer.test;/*
 * Description
 *@author Ruimeng
 *@Date 2018/12/10 13:09
 */

import com.yunda.sb.rmq.config.RabbitmqConfig;
import com.yunda.sb.rmq.config.RabbitmqConfigFanout;
import com.yunda.sb.rmq.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RMQProducerTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void testSendByTopics(){
        for (int i=0;i<5;i++){
            String message = "生产者  sms email inform to user"+i;
            rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM,"inform.sms.email",message);
            System.out.println("生产者  Send Message is:'" + message + "'");
        }
    }

    @Test
    public void testSendByTopics2(){

        User user = new User("张三",18 );
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM,"inform.sms.email",user);

      /*  for (int i=0;i<5;i++){
            String message = "生产者  sms email inform to user"+i;
            rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM,"inform.sms.email",message);
            System.out.println("生产者  Send Message is:'" + message + "'");
        }*/
    }

    @Test
    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }

    @Test
    public void send2() {
        String context = "hi, fanout msg ==========> ";
        //注意这里是3个参数，第1个交换机名称，第2个路由键默认为空，第3个为消息内容。
        this.rabbitTemplate.convertAndSend("fanoutExchange","", context);
        System.out.println("Sender : " + context);
       // this.rabbitTemplate.convertAndSend(RabbitmqConfigFanout.EXCHANGE_TOPICS_INFORM,"", context);
    }


    @Test
    public void sendAA() {
        String context = "hi AA, this is  msg to AA queue ";
        //注意这里是3个参数，第1个交换机名称，第2个路由键，第3个为消息内容。
        this.rabbitTemplate.convertAndSend("routingExchange","AA", context);
        System.out.println("Sender : " + context);
    }
    @Test
    public void sendBB() {
        String context = "hi BB, this is  msg to BB queue ";
        //注意这里是3个参数，第1个交换机名称，第2个路由键，第3个为消息内容。
        this.rabbitTemplate.convertAndSend("routingExchange","BB", context);
        System.out.println("Sender : " + context);
    }

    //*表示一个词.
    //  #表示零个或多个词.
    @Test
    public void sendTopicA() {
        String context = "hi TopicA, 这个消息只能由A队列接受 ";
        //注意这里是3个参数，第1个交换机名称，第2个路由键，第3个为消息内容 topicA的路由模式，topic.#.A.*
        this.rabbitTemplate.convertAndSend("topicsExchange","topic.A.info", context);
        System.out.println("Sender : " + context);
    }
    @Test
    public void sendTopicB() {
        String context = "hi TopicB, 这个消息只能由B队列接受";
        //注意这里是3个参数，第1个交换机名称，第2个路由键，第3个为消息内容。topicB的路由模式，topic.*.B.#
        this.rabbitTemplate.convertAndSend("topicsExchange","topic.info.B", context);
        System.out.println("Sender : " + context);
    }
    @Test
    public void sendTopicC() {
        String context = "hi TopicA and TopicB , 这个消息可以由A队列，B队列接受";
        //注意这里是3个参数，第1个交换机名称，第2个路由键，第3个为消息内容。
        this.rabbitTemplate.convertAndSend("topicsExchange","topic.A.B", context);
        System.out.println("Sender : " + context);
    }


}
