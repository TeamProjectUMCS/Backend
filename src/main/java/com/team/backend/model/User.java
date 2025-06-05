package com.team.backend.model;

import com.team.backend.model.Enum.Preference;
import com.team.backend.model.Enum.Sex;
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
@RequiredArgsConstructor(staticName = "of")
// wszystkie non null pola moga byc wziete do konstruktora UWAGA NOn NULL musi byc z lomboka a nie z jakarty
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

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false)
    private Sex sex;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "preference", nullable = false)
    private Preference preference;
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

    private String description = "";

    private int age;
    private String localization;
    private int age_min;
    private int age_max;
//    private int distancePreference;


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