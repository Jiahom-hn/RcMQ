package com.oujiong.service.produce.mqseivice.Demo.Producer;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

public class TransactionMQProducerDemo {

    public static void main(String[] args) throws Exception{

        TransactionMQProducer producer = new TransactionMQProducer("TransactionMQProducerDemoG");
        producer.setNamesrvAddr("");
        producer.start();

        Message msg = new Message("test", "*", "1", "test".getBytes());

        producer.sendMessageInTransaction(msg, new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object o) {

                // 执行事务
                System.out.println(JSON.toJSONString(message));

                //提交
                return LocalTransactionState.COMMIT_MESSAGE;
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                //超时或者上一步返回 unkonw

                // 记得做幂等
                byte[] body = messageExt.getBody();
                System.out.println(JSON.toJSONString(messageExt));
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });

        //不要关闭队列。


    }
}
