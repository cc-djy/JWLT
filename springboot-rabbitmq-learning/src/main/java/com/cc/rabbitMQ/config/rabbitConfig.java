package com.cc.rabbitMQ.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class rabbitConfig {

    /**
     * 下面三个是消息队列精确匹配
     *
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    @Bean
    public Queue directQueue() {
        return new Queue("directQueue", true);
    }

    /**
     * 建立交换机与队列的关系
     *
     * @param directQueue
     * @param directExchange
     * @return
     */
    @Bean
    public Binding bindingDirectExchange(Queue directQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue).to(directExchange).with("directExchange");
    }

    /**
     * 下面为不需要匹配
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanoutQueue1", true);
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanoutQueue2", true);
    }

    @Bean
    public Binding bindingFanoutExchange1(Queue fanoutQueue1, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }

    @Bean
    public Binding bindingFanoutExchange2(Queue fanoutQueue2, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }

    //=======================模糊匹配=========================
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    public Queue topicQueue1() {
        return new Queue("topicQueue1", true);
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue("topicQueue2", true);
    }

    @Bean
    public Binding bindingTopicExchange1(Queue topicQueue1, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueue1).to(topicExchange).with("*.topic1.#");
    }

    @Bean
    public Binding bindingTopicExchange2(Queue topicQueue2, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueue2).to(topicExchange).with("*.topic2.#");
    }

}
