package com.team.backend.service;

import com.team.backend.model.Hobby;
import com.team.backend.repository.HobbyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class HobbyService {

    private final HobbyRepository hobbyRepository;

    public Hobby createHobby(Hobby hobby) {
        return hobbyRepository.save(hobby);
    }

    public Optional<Hobby> findHobbyByName(String hobbyName) {
        return hobbyRepository.findByHobby_name(hobbyName);
    }

    public Optional<Hobby> findHobbyById(Long id) {
        return hobbyRepository.findById(id);
    }

    public void deleteHobby(Long id) {
        hobbyRepository.deleteById(id);
    }

    public Hobby updateHobby(Hobby hobby) {
        return hobbyRepository.save(hobby);
    }
}
