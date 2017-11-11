/*package com.rbsample.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.ConnectionFactory;

@Configuration
public class RabbitMqConfig {
	
	@Value("${my.rabbitmq.host}")
	String rabbitmqHost;
	
	@Value("${my.rabbitmq.port}")
	String rabbitmqPort;
	
	@Value("${my.rabbitmq.username}")
	String username;
	
	@Value("${my.rabbitmq.password}")
	String password;
	
	   @Bean
	    public ConnectionFactory rabbitConnectionFactory(){
	        ConnectionFactory connectionFactory = new ConnectionFactory();
	        connectionFactory.setHost(rabbitmqHost);
	        //connectionFactory.setPort(Integer.valueOf(rabbitmqPort));
	        //connectionFactory.setUsername(username);
	        //connectionFactory.setPassword(password);
	        connectionFactory.setAutomaticRecoveryEnabled(true);
	        // more config options here etc
	        return connectionFactory;
	    }
}
*/