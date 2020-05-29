package com.challenge.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Challenge {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    @Size(max =  100)
    private String name;

    @NotNull
    @NotBlank
    @Size(max =  50)
    private String slug;

    @NotNull
    @NotBlank
    @CreatedDate
    private LocalDate createdAt;

    @OneToMany
    private List<Acceleration> accelerationList;

    @OneToMany
    private List<Submission> submissionList;
}
