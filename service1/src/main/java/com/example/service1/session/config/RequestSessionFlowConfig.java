package com.example.service1.session.config;

import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.messaging.MessageChannel;

@Configuration
public class RequestSessionFlowConfig
{

    @Bean
    public MessageChannel requestNewSessionChannel()
    {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow requestSessionFlow(MessageChannel requestNewSessionChannel, AsyncRabbitTemplate amqpTemplate)
    {
        return flow -> flow.channel(requestNewSessionChannel)
                .handle(Amqp.asyncOutboundGateway(amqpTemplate)
                        .exchangeName("")
                        .routingKey("test-queue"));
    }

    @Bean
    public AsyncRabbitTemplate asyncRabbitTemplate(RabbitTemplate rabbitTemplate)
    {
        AsyncRabbitTemplate template = new AsyncRabbitTemplate(rabbitTemplate);
        template.setReceiveTimeout(10000);

        return template;
    }
}
