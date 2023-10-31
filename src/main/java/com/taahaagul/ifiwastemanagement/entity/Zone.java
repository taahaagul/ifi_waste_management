package com.taahaagul.ifiwastemanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Zone {

    @Id
    @GeneratedValue
    private Long id;
    private String zoneName;
    private String zoneCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private Branch branch;
}
