package com.cc.controller.MqttController;

import com.alibaba.fastjson.JSONObject;
import com.cc.server.MqttServer;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqttController {

    @Autowired
    private MqttServer mqttServer;

    @PostMapping("connect")
    public Boolean connect(){
        mqttServer.connect();
        return true;
    }

    @PostMapping("subscribe")
    public Boolean subscribe(@RequestBody JSONObject object){
        int qos = 0;
        if (object.getInteger("qos") != null){
            qos = object.getInteger("qos");
        }
        System.out.println(qos);
        mqttServer.subscribe(object.getString("topic"),qos);
        return true;
    }

    @PostMapping("publish")
    public Boolean publish(@RequestBody JSONObject object){
        mqttServer.publish(object.getString("topic"),object.getString("message"));
        return true;
    }

    @PostMapping("close")
    public Boolean publish() throws MqttException {
        mqttServer.close();
        return true;
    }

}
