package com.eu.servicemybatis.mq.sender;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author yuanjie
 * @date 2018/12/12
 */
@Component
public class MsgSendConfirmCallBack implements RabbitTemplate.ConfirmCallback, InitializingBean {
    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 实现ConfirmCallback
     * ACK=true 仅仅标识消息已被Broker接收到,并不标识已成功投放至消息队列中
     * ACK=false 表示消息由于roker处理错误,消息并未处理成功
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("消息id: " + correlationData + "确认 " + (ack ? "成功 " : "失败 "));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        rabbitTemplate.setConfirmCallback(this::confirm);
    }
}
