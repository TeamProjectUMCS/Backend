package com.team.backend.service;

import com.team.backend.model.Message;
import com.team.backend.repository.MessageRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;


    public List<Message> findByMatchId(Long matchId) {
        return messageRepository.findByMatchIdOrderByTimestampDesc(matchId);
    }

    public Message getLastMessageFromMatch(Long matchId) {
        return messageRepository.findFirstByMatchIdOrderByTimestampDesc(matchId).orElse(null);
    }

    @Transactional
    public void save(Message message) {
        messageRepository.save(message);
    }

    @Transactional
    public void delete(Message message) {
        messageRepository.delete(message);
    }
}
