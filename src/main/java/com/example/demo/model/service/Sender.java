package com.example.demo.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.ChattingMessage;

@Component
public class Sender {

	private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

	@Autowired
	private KafkaTemplate<String, ChattingMessage> kafkaTemplate;

	public void send(String topic, ChattingMessage data) {
		LOGGER.info("sending   data='{}'    to    topic='{}'", data, topic);
		kafkaTemplate.send(topic, data);// send to react clients via websocket(STOMP)
	}
}
