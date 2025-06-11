package com.team.backend.service;

import com.team.backend.model.Hobby;
import com.team.backend.repository.HobbyRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HobbyService {

    private final HobbyRepository hobbyRepository;

    public Hobby createHobby(Hobby hobby) {
        return hobbyRepository.save(hobby);
    }
//
//    public Optional<Hobby> findHobbyByName(com.team.backend.model.Enum.Hobby hobbyName) {
//        return hobbyRepository.findByName(hobbyName);
//    }


    public Optional<Hobby> findHobbyById(Long id) {
        return hobbyRepository.findById(id);
    }

    public void deleteHobby(Long id) {
        hobbyRepository.deleteById(id);
    }

    public Hobby updateHobby(Hobby hobby) {
        return hobbyRepository.save(hobby);
    }

    public List<Hobby> getHobbiesByIdList(List<Long> idList) {
        List<Hobby> result = new ArrayList<>();

        for (Long id : idList) {
            hobbyRepository.findById(id).ifPresent(result::add);
        }

        return result;
    }
//
//    public List<Hobby> searchHobbiesByKeyword(String keyword, int limit) {
//        return hobbyRepository.findByHobbyNameContainingIgnoreCase(keyword, Limit.of(limit));
//    }
}
