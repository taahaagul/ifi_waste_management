package com.taahaagul.ifiwastemanagement.dto;

import org.springframework.data.domain.PageImpl;

import java.util.List;

public class CustomPage<T> extends PageImpl<T> {
    private long totalActive;
    private long totalPassive;

    public CustomPage(List<T> content, long totalActive, long totalPassive) {
        super(content);
        this.totalActive = totalActive;
        this.totalPassive = totalPassive;
    }

    public long getTotalActive() {
        return totalActive;
    }

    public long getTotalPassive() {
        return totalPassive;
    }
}
