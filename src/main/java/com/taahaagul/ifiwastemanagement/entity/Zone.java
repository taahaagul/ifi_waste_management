package com.taahaagul.ifiwastemanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @OneToMany(mappedBy = "zone", fetch = FetchType.LAZY)
    private List<Customer> customers;
}