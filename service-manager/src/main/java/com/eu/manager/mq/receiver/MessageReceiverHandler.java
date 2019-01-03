package com.eu.manager.mq.receiver;

import com.eu.util.amqp.MqConstants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author yuanjie
 * @date 2018/12/12
 */
@Slf4j
@Component
public class MessageReceiverHandler {
	@Autowired
	private RedisTemplate redisTemplate;

	private static final Logger LOG = LoggerFactory.getLogger(MessageReceiverHandler.class);

	@RabbitHandler
	@RabbitListener(queues = { MqConstants.directQueueName })
	public void testMessage(String s){
		redisTemplate.opsForValue().get("user:"+ s);
		System.out.println(s);
	}

}
