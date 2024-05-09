package com.taahaagul.ifiwastemanagement.dto.liteDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Builder
public class UserLiteDTO {

    private Long id;
    private String username;
    private String mobilePhone;
    private boolean enabled;
}
