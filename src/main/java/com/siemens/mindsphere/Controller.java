package com.siemens.mindsphere;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	private static Logger logger = LoggerFactory.getLogger(Controller.class);

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@RequestMapping(method = RequestMethod.GET, value = "/sendqueue")
	public ResponseEntity<?> sendToQueue() {
		try {
			final CustomMessage message = new CustomMessage("Hello there!", new Random().nextInt(50), false);
			rabbitTemplate.convertAndSend("spring-direct-exchange", "testingqueue", message);
		} catch (Exception e) {
			logger.info((e.toString()));
		}

		return new ResponseEntity<String>("abc", HttpStatus.OK);
	}

	// @RabbitListener(queues = "abc")
	// public String onMessage(String message){
	// logger.debug("OnMessage :"+message);
	// return message;
	// }

}
