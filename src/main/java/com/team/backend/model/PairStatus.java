package com.team.backend.model;

import com.team.backend.model.Enum.LikedStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "pair_status")
public class PairStatus
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "liked_status", nullable = false)
    private LikedStatus likedStatus;

    public PairStatus(User user, LikedStatus likedStatus) {
        this.user = user;
        this.likedStatus = likedStatus;
    }
}
