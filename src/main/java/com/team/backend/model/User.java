package com.team.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of") // wszystkie non null pola moga byc wziete do konstruktora UWAGA NOn NULL musi byc z lomboka a nie z jakarty
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull //Non Null z lombok i nie nullable columna
    @Column(nullable = false, unique = true)
    private String username;
    @NonNull
    @Column(nullable = false)
    private String login;
    @NonNull
    @Column(nullable = false)
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



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}