package com.team.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pending_pair")
public class PendingPair
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "first_user_status_id")
    private PairStatus firstUserStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "second_user_status_id")
    private PairStatus secondUserStatus;
}
