package az.turing.tinderapp.service;

import az.turing.tinderapp.dto.MessageDto;
import az.turing.tinderapp.entity.Message;
import az.turing.tinderapp.entity.User;
import az.turing.tinderapp.exception.NotFoundException;
import az.turing.tinderapp.mapper.MessageMapper;
import az.turing.tinderapp.repo.MessageRepo;
import az.turing.tinderapp.repo.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepo messageRepo;
    private final MessageMapper messageMapper;
    private final UserRepo userRepo;
    public List<MessageDto> getAllMessages() {
        List<Message> messages = messageRepo.findAll();
        return messages.stream().map(messageMapper::toDto).collect(Collectors.toList());
    }

    public MessageDto saveMessage(MessageDto messageDto) {
        User sender = userRepo.findById(messageDto.getSenderId())
                .orElseThrow(() -> new NotFoundException("Sender not found"));
        User receiver = userRepo.findById(messageDto.getReceiverId())
                .orElseThrow(() -> new NotFoundException("Receiver not found"));
        Message message = new Message();
        message.setMessage(messageDto.getMessage());
        message.setSenderId(sender.getId());
        message.setReceiverId(receiver.getId());
        if(message.getTimestamp()==null) message.setTimestamp(LocalDateTime.now());
        Message savedMessage = messageRepo.save(message);
        return messageMapper.toDto(savedMessage);
    }

    public MessageDto getMessageById(Long id) {
        return messageRepo.findById(id).map(messageMapper::toDto)
                .orElseThrow(()->new NotFoundException("Message has not found"));
    }

    public void deleteMessageById(Long id) {
        messageRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        messageRepo.deleteById(id);
    }
    public MessageDto updateMessage(Long id, MessageDto messageDto) {
        Message message = messageRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Message not found"));

        User sender = userRepo.findById(messageDto.getSenderId())
                .orElseThrow(() -> new NotFoundException("Sender not found"));
        User receiver = userRepo.findById(messageDto.getReceiverId())
                .orElseThrow(() -> new NotFoundException("Receiver not found"));

        message.setMessage(messageDto.getMessage());
        message.setSenderId(sender.getId());
        message.setReceiverId(receiver.getId());
        message.setTimestamp(LocalDateTime.now());

        Message savedMessage = messageRepo.save(message);
        return messageMapper.toDto(savedMessage);
    }


}

