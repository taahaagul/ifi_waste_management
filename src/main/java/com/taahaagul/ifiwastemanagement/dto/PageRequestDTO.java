package com.taahaagul.ifiwastemanagement.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Objects;

@Getter
@Setter
public class PageRequestDTO {

    private Integer pageNo = 0;
    private Integer pageSize = 10;
    private Sort.Direction sort = Sort.Direction.ASC;
    private String sortByColumn = "id";

    public Pageable getPageable(PageRequestDTO dto) {
        Integer page = Objects.nonNull(dto.getPageNo()) ? dto.getPageNo() : this.pageNo;
        Integer size = Objects.nonNull(dto.getPageSize()) ? dto.getPageSize() : this.pageSize;
        Sort.Direction sort = Objects.nonNull(dto.getSort()) ? dto.getSort() : this.sort;
        String sortByColumn = Objects.nonNull(dto.getSortByColumn()) ? dto.getSortByColumn() : this.sortByColumn;

        PageRequest request = PageRequest.of(page, size, Sort.by(sort, sortByColumn));
        return request;
    }
}
