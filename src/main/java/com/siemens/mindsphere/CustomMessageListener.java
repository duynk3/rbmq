package com.siemens.mindsphere;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.siemens.mindsphere.CustomMessage;

@Service
public class CustomMessageListener {

	private static Logger logger = LoggerFactory.getLogger(CustomMessageListener.class);

//    @RabbitListener(queues = "testingqueue")
//    public void receiveMessage(final Message message) {
//    	logger.info("Received message as generic: {}", message.toString());
//    }

    @RabbitListener(queues = "testingqueue")
    public void receiveMessage(final CustomMessage customMessage) {
    	logger.info("Received message as specific class: {}", customMessage.toString());
    }
}
