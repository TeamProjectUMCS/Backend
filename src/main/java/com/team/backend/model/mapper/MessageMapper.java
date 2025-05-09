package com.team.backend.model.mapper;

import com.team.backend.model.Match;
import com.team.backend.model.Message;
import com.team.backend.model.User;
import com.team.backend.model.dto.MessageResponseDto;
import com.team.backend.model.dto.MessageRequestDto;
import org.springframework.stereotype.Service;

@Service
public class MessageMapper {
    public static Message toEntity(MessageRequestDto dto, User sender, Match match) {
        Message message = new Message();
        message.setContent(dto.content());
        message.setWrittenBy(sender.getId());
        message.setMatch(match);
        message.setTimestamp(System.currentTimeMillis());
        return message;
    }

    public static MessageResponseDto mapToMessageResponse(Message message) {
        return new MessageResponseDto(
                message.getMessageId(),
                message.getContent(),
                message.getWrittenBy(),
                message.getTimestamp()
        );
    }
}

