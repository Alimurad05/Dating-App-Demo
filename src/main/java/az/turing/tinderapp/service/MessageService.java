package az.turing.tinderapp.service;

import az.turing.tinderapp.dto.MessageDto;
import az.turing.tinderapp.entity.Message;
import az.turing.tinderapp.mapper.MessageMapper;
import az.turing.tinderapp.repo.MessageRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepo messageRepo;
    private final MessageMapper messageMapper;

    public List<MessageDto> getAllMessages() {
        List<Message> messages = messageRepo.findAll();
        return messages.stream().map(messageMapper::toDto).collect(Collectors.toList());
    }

    public MessageDto saveMessage(MessageDto messageDto) {
        Message message = new Message();
        message.setMessage(messageDto.getMessage());
        message.setSenderId(messageDto.getSenderId());
        message.setReceiverId(messageDto.getReceiverId());
        message.setTimestamp(messageDto.getTimestamp());
        Message savedMessage = messageRepo.save(message);
        return messageMapper.toDto(savedMessage);
    }

    public MessageDto getMessageById(Long id) {
        return messageRepo.findById(id).map(messageMapper::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    public void deleteMessageById(Long id) {
        messageRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        messageRepo.deleteById(id);
    }
}

