package com.taahaagul.ifiwastemanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    private String customerName;
    private String houseNumber;
    private String mobileNumber;
    private String specialRate;
    private String latitude;
    private String longitude;
    private boolean enabled;
    private LocalDateTime createdAt;
    private String creeatedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    private Zone zone;
}
