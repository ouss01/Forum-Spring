package com.programming.techie.springngblog.repository;

import com.programming.techie.springngblog.model.ChatMessage;
import com.programming.techie.springngblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
 @Repository
public interface ChatRepository extends JpaRepository<ChatMessage,Long> {

    // public List<ChatMessage> getAllMessagesForUser ( Long userId);

     @Query("SELECT DISTINCT receiver.id FROM ChatMessage WHERE sender.id = :senderId")
     List<Long> findAllReceiverIdsBySenderId(@Param("senderId") Long senderId);


}
