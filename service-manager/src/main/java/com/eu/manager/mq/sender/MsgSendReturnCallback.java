package com.eu.manager.mq.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author yuanjie
 * @date 2018/12/12
 */
@Slf4j
@Component
public class MsgSendReturnCallback implements RabbitTemplate.ReturnCallback, InitializingBean {
    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 实现ReturnCallback
     * 当消息发送出去找不到对应路由队列时,将会把消息退回
     * 如果又任何一个路由队列接收投递消息成功,则不会退回消息
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//        System.out.println("消息发送失败："+ Arrays.toString(message.getBody()));
        log.debug("消息发送失败："+ Arrays.toString(message.getBody()));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        rabbitTemplate.setReturnCallback(this::returnedMessage);
    }
}
