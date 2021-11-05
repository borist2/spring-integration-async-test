package com.example.service1.session.gateway;

import com.example.service1.session.dto.RequestDTO;
import com.example.service1.session.dto.SessionCreationResponseDTO;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.util.concurrent.ListenableFuture;

@MessagingGateway(asyncExecutor = "taskExecutor")
public interface SessionGateway
{
    @Gateway(requestChannel = "requestNewSessionChannel")
    ListenableFuture<SessionCreationResponseDTO> requestNewSession(RequestDTO request);
}
