package com.team.backend.service;

import com.team.backend.model.Message;
import com.team.backend.repository.MessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }


    public List<Message> findByMatchId(Long matchId) {
        return messageRepository.findByMatchId(matchId);
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
