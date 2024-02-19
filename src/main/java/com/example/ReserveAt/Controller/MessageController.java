package com.example.ReserveAt.Controller;

import com.example.ReserveAt.Dto.MessageDTO;
import com.example.ReserveAt.Service.MessageService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<MessageDTO> sendMessage(@RequestBody MessageDTO messageDTO) {
        try {
            MessageDTO sentMessageDTO = messageService.sendMessage(messageDTO);
            System.out.println("message dto: " + messageDTO);
            System.out.println("sent message dto: " + sentMessageDTO);
            return new ResponseEntity<>(sentMessageDTO, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MessageDTO>> getMessageForUser(@PathVariable Long userId) {
        List<MessageDTO> messages = messageService.getMessagesForUser(userId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/business/{businessId}")
    public ResponseEntity<List<MessageDTO>> getMessagesForBusiness(@PathVariable Long businessId) {
        List<MessageDTO> messages = messageService.getMessagesForBusiness(businessId);
        return ResponseEntity.ok(messages);
    }
}
