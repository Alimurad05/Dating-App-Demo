package az.turing.tinderapp.controller;

import az.turing.tinderapp.dto.MessageDto;
import az.turing.tinderapp.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
    @GetMapping
    public ResponseEntity<List<MessageDto>> getAllMessages(){
        return ResponseEntity.ok(messageService.getAllMessages());
    }
    @GetMapping("/{id}")
    public ResponseEntity<MessageDto> getMessageById(@PathVariable Long id){
        return ResponseEntity.ok(messageService.getMessageById(id));
    }
    @PostMapping
    public ResponseEntity<MessageDto> saveMessage(@RequestBody MessageDto messageDto){
        return ResponseEntity.ok(messageService.saveMessage(messageDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessageById(@PathVariable Long id){
        messageService.deleteMessageById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<MessageDto> updateMessage(@PathVariable Long id, @RequestBody MessageDto messageDto){
        return ResponseEntity.ok(messageService.saveMessage(messageDto));
    }

}
