package com.cc.rabbitMQ.service.direct;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RabbitListener(queues = "directQueue") //监听某一个队列
public class MessageDirectReceiver {

    @RabbitHandler
    public void process(Object object, Channel channel, Message message) throws IOException {
        try {
            Thread.sleep(3000);
            System.out.println("睡眠3秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            System.out.println("receive success:"+object);
        }catch (Exception e){
            e.printStackTrace();
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
            System.out.println("receive fail");
        }
    }
}
