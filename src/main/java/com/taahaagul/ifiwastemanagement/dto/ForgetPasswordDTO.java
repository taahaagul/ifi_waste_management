package com.taahaagul.ifiwastemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ForgetPasswordDTO {

    @NotEmpty(message = "token can not be a null or empty")
    private String token;
    @NotBlank(message = "newPasw can not be a null or empty")
    private String newPasw;
}
