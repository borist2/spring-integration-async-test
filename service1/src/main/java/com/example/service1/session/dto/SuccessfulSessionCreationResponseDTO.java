package com.example.service1.session.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.extern.jackson.Jacksonized;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
@Jacksonized
@NonFinal
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SuccessfulSessionCreationResponseDTO extends SessionCreationResponseDTO
{

    @Builder(toBuilder = true)
    public SuccessfulSessionCreationResponseDTO()
    {
        super(ResponseStatus.SUCCESS);
    }
}
