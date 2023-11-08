package com.taahaagul.ifiwastemanagement.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ForgetPaswRequest {

    @NotEmpty(message = "Token can not be a null or empty")
    private String token;
    @NotEmpty(message = "newPasw can not be a null or empty")
    private String newPasw;
}
