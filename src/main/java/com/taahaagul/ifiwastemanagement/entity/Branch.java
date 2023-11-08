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
public class Branch {

    @Id
    @GeneratedValue
    private Long id;
    private String branchName;
    private String branchCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private District district;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Zone> zones;
}
