package com.taahaagul.ifiwastemanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Getter @Setter @ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Zone extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String zoneName;
    private String zoneCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private Branch branch;

    @OneToMany(mappedBy = "zone", fetch = FetchType.LAZY)
    private List<Customer> customers;

    @OneToMany(mappedBy = "zone", fetch = FetchType.LAZY)
    private List<Car> cars;
}