package com.cc.rabbitMQ.controller;

import com.cc.rabbitMQ.service.direct.MessageDirectSend;
import com.cc.rabbitMQ.service.direct.MessageDirectReceiver;
import com.cc.rabbitMQ.service.fanout.MessageFanoutSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/rabbit")
@RestController
public class RabbitDirectController {

    @Autowired
    private MessageDirectSend messageDirectSend;

    @Autowired
    private MessageFanoutSend messageFanoutSend;

    @GetMapping("/directSend")
    public String directSend(){
        messageDirectSend.send("directExchange","directExchange");
        return "directSend success";
    }

    @GetMapping("/fanoutSend")
    public String fanoutSend(){
        messageFanoutSend.send("fanoutExchange","");
        return "fanoutSend success";
    }

    @GetMapping("/topicSend")
    public String topicSend(){
        messageFanoutSend.send("topicExchange","cc.topic.a");
        return "topicSend success";
    }
}
