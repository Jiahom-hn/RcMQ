package com.oujiong.service.produce.mqseivice.Demo.Producer;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class Produce {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {

        DefaultMQProducer producer = new DefaultMQProducer("testGroup");
        producer.setNamesrvAddr("");
        producer.start();

        Message message = new Message("test", "*", "produce", "testproducer".getBytes());
        SendResult send = producer.send(message);

        System.out.println(JSON.toJSONString(send));

        producer.shutdown();

    }
}
