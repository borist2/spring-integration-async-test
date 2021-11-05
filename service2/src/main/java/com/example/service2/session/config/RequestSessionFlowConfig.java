package com.example.service2.session.config;

import com.example.service2.session.handler.RequestNewSessionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

@Configuration
@Slf4j
public class RequestSessionFlowConfig
{

    @Bean
    public Queue testQueue()
    {
        return new Queue("test-queue");
    }

    @Bean
    public IntegrationFlow requestNewSessionFlow(ConnectionFactory connectionFactory,
                                                 MessageConverter messageConverter,
                                                 RequestNewSessionHandler requestNewSessionHandler)
    {
        return IntegrationFlows.from(Amqp.inboundGateway(connectionFactory, "test-queue")
                .configureContainer(c -> c.defaultRequeueRejected(false))
                .messageConverter(messageConverter))
                .handle(requestNewSessionHandler)
                .get();
    }
}
