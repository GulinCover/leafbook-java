package org.leafbook.serviceCommonApi.mqService;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.leafbook.api.mqModel.EmailStructure;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MQService {
    @Value("${rocketmq.namesrvAddr}")
    private String namesrvAddr = "localhost:9876";

    public void sendEmailCode(EmailStructure emailStructure) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setVipChannelEnabled(false);
        producer.setNamesrvAddr(namesrvAddr);
        producer.setProducerGroup("emailProducerGroup");
        producer.start();
        producer.send(new Message("email", "163.email", "", JSON.toJSONBytes(emailStructure)));
    }
}
