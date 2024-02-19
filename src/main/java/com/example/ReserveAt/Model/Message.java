package com.example.ReserveAt.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "message")
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageId")
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "senderUserId", nullable = true)
    private User senderUser;

    @ManyToOne
    @JoinColumn(name = "senderBusinessId", nullable = true)
    private Business senderBusiness;

    @ManyToOne
    @JoinColumn(name = "receiverUserId", nullable = true)
    private User receiverUser;

    @ManyToOne
    @JoinColumn(name = "receiverBusinessId", nullable = true)
    private Business receiverBusiness;

    @Column(name = "messageContent")
    private String messageContent;

    @Column(name = "sendDate")
    private LocalDateTime sendDate;

    @Column(name = "reply_to_message_id")
    private Long replyToMessageId;
}
