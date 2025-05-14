package com.team.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "pending_pair")
public class PendingPair
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "first_user_status_id")
    private PairStatus firstUserStatus;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "second_user_status_id")
    private PairStatus secondUserStatus;

    public PendingPair(PairStatus firstUserStatus, PairStatus secondUserStatus) {
        this.firstUserStatus = firstUserStatus;
        this.secondUserStatus = secondUserStatus;
    }
}
