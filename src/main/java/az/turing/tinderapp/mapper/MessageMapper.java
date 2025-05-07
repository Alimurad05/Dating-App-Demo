package az.turing.tinderapp.mapper;

import az.turing.tinderapp.dto.MessageDto;
import az.turing.tinderapp.entity.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageMapper implements EntityMapper<Message, MessageDto> {
    @Override
    public Message toEntity(MessageDto messagedto) {
        return Message.builder()
                .message(messagedto.getMessage())
                .senderId(messagedto.getSenderId())
                .receiverId(messagedto.getReceiverId())
                .timestamp(messagedto.getTimestamp())
                .build();
    }

    @Override
    public MessageDto toDto(Message message) {
        return MessageDto.builder()
                .message(message.getMessage())
                .senderId(message.getSenderId())
                .receiverId(message.getReceiverId())
                .timestamp(message.getTimestamp())
                .build();
    }
}
