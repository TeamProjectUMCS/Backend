package com.team.backend.model;
import com.team.backend.model.Enum.Preference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "hobby")
public class Hobby
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private com.team.backend.model.Enum.Hobby name;


    public String getHobbyName() {return name.getDisplayName();}
}
