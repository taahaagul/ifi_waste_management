package com.taahaagul.ifiwastemanagement.dto.liteDto;

import com.taahaagul.ifiwastemanagement.dto.liteDto.UserLiteDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
@Builder
public class CarLiteDTO {

    private Long id;
    private String targoNo;
    private boolean status;

    private List<UserLiteDTO> users;
}
