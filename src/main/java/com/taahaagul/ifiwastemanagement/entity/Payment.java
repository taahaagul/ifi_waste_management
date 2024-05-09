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
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column(columnDefinition = "DATETIME")
    LocalDateTime paymentDate;

    @OneToMany(mappedBy = "payment", fetch = FetchType.LAZY)
    List<Operation> operations;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
