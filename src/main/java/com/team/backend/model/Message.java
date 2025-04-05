package com.team.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "messages")
public class Message
{

    @Id
    private Long messageId;

    private int writtenBy;

    private String content;

    private Long timestamp;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    //private boolean reaction;   XDD nie ma mowy ze to bede implementowal na "froncie"
}
