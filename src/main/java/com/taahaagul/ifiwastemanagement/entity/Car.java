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
public class Car extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String targoNo;
    private String ownerName;
    private String ownerSurname;
    private String ownerPhone;
    private String taxFee;
    private String vehicleClass;
    private String amount;
    private String fuelType;
    private boolean status;

    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    private List<User> users;

    @ManyToOne(fetch = FetchType.LAZY)
    private Zone zone;
}
