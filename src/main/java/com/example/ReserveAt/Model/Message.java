package com.example.ReserveAt.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name = "message")
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageID")
    private Long messageId;
    @Column(name = "senderID")
    private Long senderId;
    @Column(name = "receiverID")
    private Long receiverId;
    @Column(name = "messageContent")
    private String messageContent;
    @Column(name = "sendDate")
    private Date sendDate;
}
