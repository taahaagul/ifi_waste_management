package com.taahaagul.ifiwastemanagement.dto;

import com.taahaagul.ifiwastemanagement.dto.liteDto.CarLiteDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @ToString
@Builder
public class ZoneDTO {

    private Long id;

    @NotEmpty(message = "zoneName cannot be null or empty")
    private String zoneName;
    @NotEmpty(message = "zoneCode cannot be null or empty")
    private String zoneCode;

    private Long branchId;
    private String branchName;
    private Long districtId;
    private String districtName;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    private List<CarLiteDTO> cars;
}