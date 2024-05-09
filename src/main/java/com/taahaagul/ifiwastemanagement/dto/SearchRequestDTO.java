package com.taahaagul.ifiwastemanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDTO {

    private String column;
    private String value;
    private Operation operation;
    private String joinTable;
    private boolean formatDate;
    private boolean formatBoolean;

    public enum Operation {
        EQUAL,
        LIKE,
        IN,
        GREATER_THAN,
        LESS_THAN,
        BETWEEN,
        JOIN, 
        NOT_EQUAL;
    }
}