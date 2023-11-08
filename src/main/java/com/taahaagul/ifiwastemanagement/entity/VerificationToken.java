package com.taahaagul.ifiwastemanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VerificationToken {

    @Id
    @GeneratedValue
    private Long id;
    private String token;
    private Date expirationTime;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;
}
