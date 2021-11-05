package com.example.service2.shared.config;

import com.example.service2.session.dto.RequestDTO;
import com.example.service2.session.dto.SuccessfulSessionCreationResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MessageConverterConfig
{
    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper, ClassMapper classMapper)
    {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter(objectMapper);
        converter.setClassMapper(classMapper);

        return converter;
    }

    @Bean
    public DefaultJackson2JavaTypeMapper defaultJackson2JavaTypeMapper()
    {
        DefaultJackson2JavaTypeMapper defaultJackson2JavaTypeMapper = new DefaultJackson2JavaTypeMapper();

        Map<String, Class<?>> mappings = new HashMap<>();
        mappings.put(RequestDTO.class.getSimpleName(), RequestDTO.class);
        mappings.put(SuccessfulSessionCreationResponseDTO.class.getSimpleName(), SuccessfulSessionCreationResponseDTO.class);
        defaultJackson2JavaTypeMapper.setIdClassMapping(mappings);

        return defaultJackson2JavaTypeMapper;
    }
}
