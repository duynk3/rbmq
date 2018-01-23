package com.siemens.mindsphere;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableRabbit
@Profile("cloud")
public class CloudConf extends AbstractCloudConfig {

	// private static Logger logger =
	// LoggerFactory.getLogger(AbstractCloudConfig.class);

	/* ---------------------- */
	/* RabbitMQ Configuration */
	/* ---------------------- */
	@Bean
	public ConnectionFactory initRabbitConnectionFactory() {
		return connectionFactory().rabbitConnectionFactory();
	}

	@Bean
	public Queue orchestratorToJobManagerQueue() {
		return new Queue("testingqueue", true);
	}

	@Bean
	public DirectExchange orchestratorToJobManagerDirectExchange() {
		return new DirectExchange("spring-direct-exchange");
	}

	@Bean
	public Binding orchestratorToJobManagerBinding(Queue orchestratorToJobManagerQueue,
			DirectExchange orchestratorToJobManagerDirectExchange) {
		return BindingBuilder.bind(orchestratorToJobManagerQueue).to(orchestratorToJobManagerDirectExchange)
				.with("testingqueue");
	}

}