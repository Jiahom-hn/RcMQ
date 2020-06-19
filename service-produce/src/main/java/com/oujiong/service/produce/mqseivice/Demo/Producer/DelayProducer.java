package com.oujiong.service.produce.mqseivice.Demo.Producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.UUID;

/**
 * 延迟消息
 */
public class DelayProducer {



    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("test");

        producer.setNamesrvAddr("");

        producer.start();


        ArrayList<Message> messages = new ArrayList<>();
        Message message = new Message("testTopic","tag1", UUID.randomUUID().toString(),"ssss".getBytes());

        messages.add(message);


        // 消息大小超过4M要切割
        SendResult send = producer.send(messages);

        producer.shutdown();



    }
}
