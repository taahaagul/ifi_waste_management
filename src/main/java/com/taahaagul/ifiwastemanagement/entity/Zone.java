package com.taahaagul.ifiwastemanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Zone {

    @Id
    @GeneratedValue
    private Long id;
    private String zoneName;
    private String zoneCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private Branch branch;
}
