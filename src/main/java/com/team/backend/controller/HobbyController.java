package com.team.backend.controller;

import com.team.backend.model.Hobby;
import com.team.backend.service.HobbyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/hobby")
public class HobbyController {
    private HobbyService hobbyService;

//    @GetMapping("/search")
//    public List<Hobby> searchHobby(@RequestParam String query) {
//        return hobbyService.searchHobbiesByKeyword(query, 5);
//    }
}
