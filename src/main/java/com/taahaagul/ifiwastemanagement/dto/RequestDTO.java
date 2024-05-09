package com.taahaagul.ifiwastemanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestDTO {

    private List<SearchRequestDTO> searchRequestDto;
    private GlobalOperator globalOperator;
    private PageRequestDTO pageRequestDto;

    public enum GlobalOperator {
        AND,
        OR;
    }
}
