package org.leafbook.serviceMQApi.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.leafbook.api.mqModel.EmailStructure;
import org.leafbook.serviceMQApi.model.EmailAbs;
import org.leafbook.serviceMQApi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import java.util.List;

@Component
public class RocketMQEmail {
    @Autowired
    private EmailService emailService;
    @Autowired
    private RocketMQEmailProperties rocketMQEmailProperties;

    @PostConstruct
    public void sendCode() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(rocketMQEmailProperties.getDefaultGroup());
        consumer.setInstanceName(rocketMQEmailProperties.getDefaultInstance());
        consumer.setNamesrvAddr(rocketMQEmailProperties.getNamesrvAddr());
        consumer.setVipChannelEnabled(false);
        consumer.subscribe(rocketMQEmailProperties.getTopic(), rocketMQEmailProperties.getTag());
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {


                StringBuilder stringBuilder = new StringBuilder();
                for (byte b : list.get(0).getBody()) {
                    stringBuilder.append((char)b);
                }

                EmailStructure emailStructure = JSON.parseObject(stringBuilder.toString(), EmailStructure.class);
                EmailAbs emailAbs = new EmailAbs();
                emailAbs.setCode(emailStructure.getCode());
                emailAbs.setOperating("登陆");

                try {
                    emailService.send(emailStructure.getEmail(),"验证码",emailAbs);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
    }
}
