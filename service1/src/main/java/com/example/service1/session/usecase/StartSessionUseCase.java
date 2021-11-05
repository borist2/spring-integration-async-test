package com.example.service1.session.usecase;

import com.example.service1.session.dto.RequestDTO;
import com.example.service1.session.gateway.SessionGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class StartSessionUseCase
{

    private final SessionGateway sessionGateway;
    private final Random random = new Random();

    @EventListener(ApplicationReadyEvent.class)
    public void startSession(ApplicationReadyEvent event)
    {
        var sessionCreation = sessionGateway.requestNewSession(RequestDTO
                .builder().id(random.nextInt()).build());

        sessionCreation.addCallback(message -> log.debug("Message: [{}]", message), ex -> log.debug("Ex, ", ex));
    }

}
