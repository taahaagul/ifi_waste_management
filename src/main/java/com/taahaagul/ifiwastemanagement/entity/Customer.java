package com.taahaagul.ifiwastemanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String customerName;
    private String houseNumber;
    private String mobileNumber;
    private String specialRate;
    private String latitude;
    private String longitude;
    private boolean enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    private Zone zone;
}