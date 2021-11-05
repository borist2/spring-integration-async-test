package com.example.service1.session.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
@NonFinal
public abstract class SessionCreationResponseDTO
{
    ResponseStatus status;
}
