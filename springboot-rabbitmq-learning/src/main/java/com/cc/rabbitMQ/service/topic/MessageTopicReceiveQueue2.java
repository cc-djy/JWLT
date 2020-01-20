package com.cc.rabbitMQ.service.fanout;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RabbitListener(queues = "topicQueue2")
public class MessageTopicReceiveQueue2 {
    @RabbitHandler
    public void process(String hello, Channel channel, Message message) throws IOException {
        try {
            Thread.sleep(3000);
            System.out.println("睡眠3秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            System.out.println("receive topicQueue2 success:"+hello);
        }catch (Exception e){
            e.printStackTrace();
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
            System.out.println("receive topicQueue2 fail");
        }
    }
}
