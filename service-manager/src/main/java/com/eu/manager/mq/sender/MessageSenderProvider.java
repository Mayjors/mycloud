package com.eu.manager.mq.sender;

import com.eu.util.amqp.MqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author yuenjie
 * @date 2018年12月12日
 */
@Slf4j
@Component
public class MessageSenderProvider implements MessageSender {

	@Resource
	private RabbitTemplate rabbitTemplate;

	private void send(String exchange, String routkey, Object message) {
		rabbitTemplate.convertAndSend(exchange, routkey, message);
	}

	@Override
	public void sendInfo(String message) {
		send(MqConstants.EU_EXCHANGE_NAME, MqConstants.directQueueName, message);
	}

}
