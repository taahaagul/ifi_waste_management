package com.taahaagul.ifiwastemanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class VerificationToken {

    @Id
    @GeneratedValue
    private Long id;
    private String token;
    private Date expirationTime;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;
}
