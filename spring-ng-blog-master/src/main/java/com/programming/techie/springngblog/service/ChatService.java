package com.programming.techie.springngblog.service;

import com.programming.techie.springngblog.model.ChatMessage;
import com.programming.techie.springngblog.model.CurrentUser;
import com.programming.techie.springngblog.model.User;
import com.programming.techie.springngblog.repository.ChatRepository;
import com.programming.techie.springngblog.repository.UserRepository;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    private  ChatRepository chatMessageRepository;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  SimpMessagingTemplate messagingTemplate;


    public List<ChatMessage> getAllMessages() {
        return chatMessageRepository.findAll();
    }

    public ChatMessage getMessageById(Long id) {
        return chatMessageRepository.findById(id)
                .orElseThrow(null);
    }

    public List<User> getAllReceiversBySenderId(Long senderId) {
        List<Long> receiverIds = chatMessageRepository.findAllReceiverIdsBySenderId(senderId);
        List<User> receivers = userRepository.findAllById(receiverIds);
        return receivers;
    }

    public ChatMessage createMessage(String content, Long senderId, Long receiverId) {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new EntityNotFoundException("Sender not found with ID: " + senderId));

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new EntityNotFoundException("Receiver not found with ID: " + receiverId));

        ChatMessage message = new ChatMessage();
        message.setContent(content);
        message.setSender(sender);
        message.setReceiver(receiver);

        // send the message to subscribed clients
        messagingTemplate.convertAndSend("/topic/chat", message);

        return chatMessageRepository.save(message);
    }

    public ChatMessage updateMessage(Long id, String content, Long senderId, Long receiverId) {
        ChatMessage existingMessage = getMessageById(id);

        User sender = userRepository.findById(senderId)
                .orElse(null);

        User receiver = userRepository.findById(receiverId).orElse(null);

        existingMessage.setContent(content);
        existingMessage.setSender(sender);
        existingMessage.setReceiver(receiver);

        // send the updated message to subscribed clients
        messagingTemplate.convertAndSend("/topic/chat", existingMessage);

        return chatMessageRepository.save(existingMessage);
    }

    public void deleteMessage(Long id) {
        chatMessageRepository.deleteById(id);
    }
}