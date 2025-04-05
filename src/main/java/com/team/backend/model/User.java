package com.team.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    //@NotNull
    //private String email;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_hobbies",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "hobby_id")
    )
    private List<Hobby> hobbies;

    @OneToMany(mappedBy = "firstUser")
    private List<Match> matchesAsFirstUser;

    @OneToMany(mappedBy = "secondUser")
    private List<Match> matchesAsSecondUser;    //tu robocik tak powiedzial ze bedzie dobrze bo inaczej to sraka ponoć

    @OneToMany(mappedBy = "user")
    private List<PairStatus> pairStatuses;

    @ManyToOne
    @JoinColumn(name = "sex_id", nullable = false)
    private Sex sex;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_preferences",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "sex_id")
    )                                                         //tu mozliwe ze do zmiany nwm
    private List<Sex> sexPreferences;

    private int age;
    private String localization;
    private int age_min;
    private int age_max;
    private int distancePreference;
}