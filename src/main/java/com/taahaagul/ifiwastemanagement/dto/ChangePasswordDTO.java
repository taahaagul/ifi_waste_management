package com.taahaagul.ifiwastemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangePasswordDTO {

    @NotBlank(message = "Old password can not be blank")
    private String oldPasw;
    @NotBlank(message = "New password can not be blank")
    private String newPasw;
}
