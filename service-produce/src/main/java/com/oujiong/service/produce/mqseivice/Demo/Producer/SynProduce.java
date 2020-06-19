package com.oujiong.service.produce.mqseivice.Demo.Producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * 异步消息发送
 */
public class SynProduce {


    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException {

        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("syGroup");
        defaultMQProducer.setNamesrvAddr("192.168.11.11");
        defaultMQProducer.start();

        Message message = new Message("SyTest","*","syMsg","sytest".getBytes());
        defaultMQProducer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {

                System.out.println("发送成功");

            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println("发送异常");
            }
        });

        defaultMQProducer.shutdown();



    }
}
