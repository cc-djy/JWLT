package com.dgut.controller;

import com.dgut.rabbit.MessageDirectSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RequestMapping("grab")
@RestController
public class GrabCourseController {

    @Autowired
    private MessageDirectSend messageDirectSend;

    private int count = 10000;
    private CountDownLatch cdl = new CountDownLatch(count);

    @RequestMapping("course")
    public String grabCourse() {
        for (int i=0;i<count;i++){
            new Thread((Runnable)()->{
                try {
                    cdl.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                messageDirectSend.send("courseExchange-request","courseExchange-request","ok");
            }).start();
            cdl.countDown();
            try {
                TimeUnit.MICROSECONDS.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        messageDirectSend.send("courseExchange-request", "courseExchange-request", "cc");
        return "抢课成功";
    }
}
