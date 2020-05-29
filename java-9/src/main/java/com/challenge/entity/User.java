package com.challenge.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String fullName;

    @NotNull
    @NotBlank
    @Size(max =  100)
    @Email
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String nickname;

    @NotNull
    @NotBlank
    @Size(max = 255)
    private String password;

    @NotNull
    @NotBlank
    @CreatedDate
    private LocalDate createdAt;

    @OneToMany
    List<Submission> submissionList;

    @OneToMany
    List<Candidate> candidateList;
}
