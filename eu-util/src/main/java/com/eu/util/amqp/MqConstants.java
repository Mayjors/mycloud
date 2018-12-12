package com.eu.util.amqp;

/**
 * MQ基础常量
 * 
 * @author yuanjie
 * @date 2018/12/12
 */
public interface MqConstants {

	String ACS_COMMON_EXCHANGE = "direct_acsCommon";

	String HOLDER_STATUS_CHANGE_EXCHANGE = "fanout_holder_Status_Change";

	String DEAD_LETTER_EXCHANGE = "INLETPCS_X_LETTER_EXCHANGE";
	String DEAD_LETTER_QUEUE = "INLETPCS_Q_DEAD_LETTER_QUEUE";

	String EU_EXCHANGE_NAME = "EU_X_EXCHANGE";

	String directQueueName = "EU_DIRECT_QUEUE";

}