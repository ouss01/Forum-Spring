package com.programming.techie.springngblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.lang.reflect.Field;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Lob
    @Column
    @NotEmpty
    @ColumnTransformer(read = "JSON_UNQUOTE(JSON_EXTRACT(content, '$.content'))")
    private String content;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    public ChatMessage() {
    }

    public ChatMessage(String content, User sender, User reciever) {
        this.content = content;
        this.sender = sender;
        this.receiver =reciever;
    }

/*
    public static <U extends Comparable<? super U>, T> U getTimestamp(T t) {
        // First, use reflection to retrieve the timestamp field from the object.
        Field timestampField = null;
        try {
            timestampField = t.getClass().getDeclaredField("timestamp"); // replace "timestamp" with the name of your timestamp field
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        // Make sure the field is accessible
        timestampField.setAccessible(true);

        // Retrieve the timestamp value from the object
        U timestamp = null;
        try {
            timestamp = (U) timestampField.get(t);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return timestamp;
    }

 */
}
