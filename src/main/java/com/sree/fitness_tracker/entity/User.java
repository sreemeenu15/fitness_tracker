package com.sree.fitness_tracker.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private String username;

    @Column(unique = true)
    private String email;

    private String provider;
    private String providerId;

    private String password;

    private LocalDate dob;

    private boolean isActive = true;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean deleted = false;

    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Goal> goals;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BodyMetric> bodyMetrics;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<DailyActivity> dailyActivities;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Recommendation> recommendations;
}