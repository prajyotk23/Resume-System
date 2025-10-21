package com.resumesystem.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String skills;

    @Column(length = 1000)
    private String projects;

    @Column(length = 1000)
    private String internships;

    @Column(length = 1000)
    private String certificates;

    @OneToOne
    private User user;
}



