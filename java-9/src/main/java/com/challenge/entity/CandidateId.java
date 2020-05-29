package com.challenge.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class CandidateId implements Serializable {

    @ManyToOne
    private User userId;

    @ManyToOne
    private Acceleration accelerationId;

    @ManyToOne
    private Company companyId;
}
