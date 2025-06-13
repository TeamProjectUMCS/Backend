package com.team.backend.controller;

import com.team.backend.model.Match;
import com.team.backend.model.User;
import com.team.backend.model.dto.MatchDto;
import com.team.backend.model.dto.UserMatchDto;
import com.team.backend.model.mapper.MatchMapper;
import com.team.backend.model.mapper.UserMapper;
import com.team.backend.service.MatchService;
import com.team.backend.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.team.backend.model.mapper.MatchMapper.mapToMatchDto;

@RestController
@Log4j2
@AllArgsConstructor
@RequestMapping("/matches")
public class MatchController {
    private final MatchService matchService;
    private final UserService userService;

    @GetMapping("/potential")
    public ResponseEntity<List<UserMatchDto>> getPotentialMatches(Authentication authentication) {
        User currentUser = userService.getCurrentUser(authentication);

        List<UserMatchDto> result = matchService.getPotentialMatches(currentUser)
                .stream()
                .map(UserMapper::mapToUserMatchDto)
                .toList();

        return ResponseEntity.ok(result);
    }

    @PostMapping("/like/{likedUserId}")
    public ResponseEntity<?> likeUser(@PathVariable Long likedUserId, Authentication authentication) {
        String username = authentication.getName();

        boolean matchCreated = matchService.handleLike(username, likedUserId);

        if (matchCreated) {
            return ResponseEntity.ok("Match created");
        } else {
            return ResponseEntity.ok("Like registered");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<MatchDto>> getUserMatches(Authentication authentication) {
        String username = authentication.getName();
        User currentUser = userService.getUserByUsername(username);


        List<MatchDto> result = matchService.getUserMatches(currentUser)
                .stream()
                .map(match -> mapToMatchDto(match, currentUser))
                .toList();

        return ResponseEntity.ok(result);
    }
}
