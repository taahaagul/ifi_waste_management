package com.taahaagul.ifiwastemanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter @Setter @ToString
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
    private String latitude;
    private String longitude;
    private Integer customerRate;
    private boolean enabled;
    private boolean operation;
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime lastOperationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Zone zone;

    @OneToMany(mappedBy = "customer",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Operation> operations;
}