package com.programming.techie.springngblog.controller;

import com.programming.techie.springngblog.model.ChatMessage;
import com.programming.techie.springngblog.model.User;
import com.programming.techie.springngblog.repository.ChatRepository;
import com.programming.techie.springngblog.repository.UserRepository;
import com.programming.techie.springngblog.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/chat")
public class ChatController {
/*
    @Autowired
    private ChatService chatService;
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody ChatMessage chatMessage) {
        chatService.sendMessage(chatMessage);
    }
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @MessageMapping("/chat.send")
    @SendToUser("/queue/reply")
    @PostMapping("/sendMessage")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage, Principal principal) {
        chatMessage.setSender(principal.getName());
        messagingTemplate.convertAndSendToUser(chatMessage.getReciever(), "/queue/reply", chatMessage);
        return chatMessage;
    }
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    @PostMapping("/adduser")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
    @Autowired
    private ChatService chatService;
    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody ChatMessage chatMessage) {
        chatService.save(chatMessage);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{sender}/{reciever}")
    public List<ChatMessage> getMessages(@PathVariable String sender, @PathVariable String reciever) {
        return chatService.findBySenderAndReciever(sender, reciever);
    }
    @GetMapping("/{user}")
    public List<ChatMessage> getMessagesForUser(@PathVariable String user) {
        return chatService.findBySenderOrReciever(user);
    }
 */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ChatService chatMessageService;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<ChatMessage> getAllMessages() {
        return chatMessageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public ChatMessage getMessageById(@PathVariable Long id) {
        return chatMessageService.getMessageById(id);
    }

    @PostMapping("/{senderId}/{receiverId}")
    public ChatMessage createMessage(@RequestBody String content,
                                     @PathVariable Long senderId,
                                     @PathVariable Long receiverId) {
        return chatMessageService.createMessage(content, senderId, receiverId);
    }

    @PutMapping("/{id}/{senderId}/{receiverId}")
    public ChatMessage updateMessage(@PathVariable Long id, @RequestBody String content,
                                     @PathVariable Long senderId,
                                     @PathVariable Long receiverId) {
        return chatMessageService.updateMessage(id, content, senderId, receiverId);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {
        chatMessageService.deleteMessage(id);
    }


    @GetMapping("/receivers/{senderId}")
    public ResponseEntity<List<User>> getAllReceiversBySenderId(@PathVariable Long senderId) {
        List<User> receivers = chatMessageService.getAllReceiversBySenderId(senderId);
        return ResponseEntity.ok().body(receivers);
    }



}