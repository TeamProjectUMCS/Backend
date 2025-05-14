package com.team.backend.service;

import com.team.backend.model.Enum.LikedStatus;
import com.team.backend.model.Match;
import com.team.backend.model.PendingPair;
import com.team.backend.model.User;
import com.team.backend.model.dto.MessageResponseDto;
import com.team.backend.model.mapper.MessageMapper;
import com.team.backend.repository.MatchRepository;
import com.team.backend.repository.MessageRepository;
import com.team.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final PendingPairService pendingPairService;

    @Transactional
    public Match save(Match match) {
        return matchRepository.save(match);
    }

    public List<Match> findByFirstUserId(Long firstUserId) {
        return matchRepository.findByFirstUserId(firstUserId);
    }

    public List<Match> findBySecondUserId(Long secondUserId) {
        return matchRepository.findBySecondUserId(secondUserId);
    }

    public Match findById(Long id) {
        return matchRepository.findById(id).orElse(null);
    }

    public List<Match> getUserMatches(User user) {
        return matchRepository.findAllMatchesForUser(user);
    }

    public List<MessageResponseDto> findMessagesByMatchId(Long matchId) {
        return messageRepository.findByMatchId(matchId).stream()
                .map(MessageMapper::mapToMessageResponse)
                .collect(Collectors.toList());
    }

    public boolean handleLike(String username, Long likedUserId) {
        User likingUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        User likedUser = userRepository.findById(likedUserId)
                .orElseThrow(() -> new RuntimeException("Liked user not found"));

        PendingPair pendingPair = pendingPairService.getOrCreatePendingPair(likingUser, likedUser);

        pendingPairService.updatePairStatus(pendingPair, likingUser, LikedStatus.LIKED);

        if (pendingPairService.bothUsersLiked(pendingPair)) {
            matchRepository.save(new Match(likingUser, likedUser));
            pendingPairService.deletePendingPair(pendingPair);
            return true;
        }

        return false;
    }

}
