package com.team.backend.model.mapper;

import com.team.backend.model.Match;
import com.team.backend.model.User;
import com.team.backend.model.dto.MatchDto;

public class MatchMapper {

    public static MatchDto mapToMatchDto(Match match, User currentUser) {
        User matchedUser = match.getFirstUser().equals(currentUser)
                ? match.getSecondUser()
                : match.getFirstUser();

        return new MatchDto(
                match.getId(),
                matchedUser.getId(),
                matchedUser.getUsername()
        );
    }
}

