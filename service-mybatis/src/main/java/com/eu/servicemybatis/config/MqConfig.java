package com.eu.servicemybatis.config;

import com.eu.util.amqp.MqConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuanjie
 * @date 2018/12/12
 */
@Configuration
@EnableRabbit
public class MqConfig {

	protected Queue createQueue(String queueName) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("x-dead-letter-exchange", MqConstants.DEAD_LETTER_EXCHANGE);
		args.put("x-dead-letter-routing-key", MqConstants.DEAD_LETTER_QUEUE);
		Queue queue = new Queue(queueName, true, false, false, args);
		return queue;
	}

	@Bean
	public DirectExchange pcsExchange() {
		return new DirectExchange(MqConstants.EU_EXCHANGE_NAME);
	}

	@Bean
	public Queue directQueue() {
		return createQueue(MqConstants.directQueueName);
	}

	@Bean
	public Binding directQueueBinding(Queue directQueue, DirectExchange pcsExchange) {
		return BindingBuilder.bind(directQueue).to(pcsExchange).with(MqConstants.directQueueName);
	}


}