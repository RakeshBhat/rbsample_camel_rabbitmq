package com.rbsample.demo.camel.route;

import java.util.Collections;
import java.util.Map;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqConsumerRoute extends RouteBuilder {

	private static final String TIMER_RBIT = "timer://rbitProcr?fixedRate=true&period=5000&repeatCount=5";
	//private static final String RABBITMQ_XCHG_RB_RKEY_DEMO = "rabbitmq://localhost/XCHGRB_A?exchangeType=direct&queue=CAMELRBA&routingKey=Demo
	//&autoDelete=false&connectionFactory=#rabbitConnectionFactory";
	private static final String RABBITMQ_XCHG_RB_RKEY_DEMO = "rabbitmq://localhost/XCHGRB_A?exchangeType=direct&queue=CAMELRBA&routingKey=Demo&autoDelete=false";
	private static final String RABBITMQ_XCHG_RBB = "rabbitmq://localhost/XCHGRB_B?exchangeType=headers&queue=CAMELRBB&bindingArgs=#bindArgs";
	private static final String RABBITMQ_XCHGRB_A_RKEY_DEMO = "rabbitmq://localhost/XCHGRB_D";

	@Override
	public void configure() throws Exception {

		//Producer for exchange XCHGRB_D using on auto-generated temporary Q
		//(after message is delivered this Q is deleted) and with no routing key
		from(TIMER_RBIT).process(exchange -> {
			exchange.getIn().setBody("Om Sai Ram....", String.class);
		}).to(RABBITMQ_XCHGRB_A_RKEY_DEMO);

		//Consumer: Consumes messages from exchange XCHGRB_D, on auto-generated Q
		from(RABBITMQ_XCHGRB_A_RKEY_DEMO).log("${body}").end();

		//Consumer: Consumes from exchange XCHGRB_A on Q name CAMELRBA and routingKey Demo
		from(RABBITMQ_XCHG_RB_RKEY_DEMO).log("${body}").end();

		//Consumer: Consumes from exchange XCHGRB_B on Q name CAMELRBB
		from(RABBITMQ_XCHG_RBB).log("${body}").end();
	}

	@Bean(name = "bindArgs")
	public Map<String, Object> bindArgsBuilder() {
		return Collections.singletonMap("Filetype", "pdf");
	}

}
