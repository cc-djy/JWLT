package com.dgut.rabbit;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "courseQueue-response") //监听某一个队列
public class MessageDirectReceiver {
    //接收抢课信息
    @RabbitHandler
    public void process(Object object, Channel channel, Message message) throws IOException {
        System.out.println(object);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            e.printStackTrace();
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);

        }

    }
}
