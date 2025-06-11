package com.san.newsfeed.member.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor


public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false,unique=true,length=255)
    private String email;

    @Column(nullable = false,length=255)
    private String password;

    @Column(nullable = false,length=50)
    private String username;

    @Column(nullable=false)
    private LocalDateTime createdAt=LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt=LocalDateTime.now();

    @PreUpdate
    public void onUpdate() {
    this.updatedAt=LocalDateTime.now(); }
}

