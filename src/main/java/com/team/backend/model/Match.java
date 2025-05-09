package com.team.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "matches")
@Getter
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="firstUser")
    private User firstUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="secondUser")
    private User secondUser;


    @OneToMany(mappedBy = "match", fetch = FetchType.EAGER)
    private List<Message> messages;



}
