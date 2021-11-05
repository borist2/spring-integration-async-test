package com.example.service2.session.handler;

import com.example.service2.session.dto.RequestDTO;
import com.example.service2.session.dto.SessionCreationResponseDTO;
import com.example.service2.session.dto.SuccessfulSessionCreationResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SettableListenableFuture;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class RequestNewSessionHandler
{
    @ServiceActivator(async = "true")
    public ListenableFuture<SessionCreationResponseDTO> handleRequestNewSession(RequestDTO requestDTO)
    {
        SettableListenableFuture<SessionCreationResponseDTO> settableListenableFuture = new SettableListenableFuture<>();

        new Thread(() -> {
            try
            {
                new CountDownLatch(1).await(5, TimeUnit.SECONDS);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            settableListenableFuture.set(SuccessfulSessionCreationResponseDTO.builder().build());

        }).start();

        return settableListenableFuture;
    }

}
