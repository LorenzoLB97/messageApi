package com.lorenzolobrutto.messageapi.controller;

import com.lorenzolobrutto.messageapi.model.Message;
import com.lorenzolobrutto.messageapi.repository.MessageRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageRepository messageRepository;

    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @PostMapping
    public Message create(@RequestBody Message message) {
        return messageRepository.save(message);
    }
}
