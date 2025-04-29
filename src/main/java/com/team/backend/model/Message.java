package com.team.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@Table(name = "messages")
@ToString
public class Message
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // auto bo na identity cos sie psulo idk
    private Long messageId;

    private Long writtenBy;

    private String content;

    private Long timestamp;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    //private boolean reaction;   XDD nie ma mowy ze to bede implementowal na "froncie"
}
