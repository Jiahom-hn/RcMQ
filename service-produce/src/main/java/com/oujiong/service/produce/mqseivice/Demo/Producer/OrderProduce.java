package com.oujiong.service.produce.mqseivice.Demo.Producer;

import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.selector.SelectMessageQueueByHash;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;
import java.util.UUID;

public class OrderProduce {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {

        DefaultMQProducer producer = new DefaultMQProducer("deaftl");

        producer.setNamesrvAddr("");
        producer.start();

        Message test = new Message("test", "*", UUID.randomUUID().toString(), UUID.randomUUID().toString().getBytes());

        // 方式1 自己实现
        producer.send(test, new MessageQueueSelector() {
            @Override
            public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                int i = (int) o % list.size();
                return list.get(i);
            }
        },1);

        // 方式二 官方实现 ，内部其实也是Hash,
        SendResult send = producer.send(test, new SelectMessageQueueByHash(), 1);

        producer.shutdown();


    }
}
