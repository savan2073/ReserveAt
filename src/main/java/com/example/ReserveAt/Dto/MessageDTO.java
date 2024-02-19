package com.example.ReserveAt.Dto;

import java.time.LocalDateTime;

public class MessageDTO {
    private Long messageId;
    private Long senderUserId;
    private String senderFirstName;
    private String senderLastName;
    private Long senderBusinessId;
    private Long receiverUserId;
    private Long receiverBusinessId;
    private String messageContent;
    private LocalDateTime sendDate;
    private Long replyToMessageId;

    public MessageDTO() {
    }

    public MessageDTO(Long messageId, Long senderUserId, String senderFirstName, String senderLastName, Long senderBusinessId, Long receiverUserId, Long receiverBusinessId, String messageContent, LocalDateTime sendDate, Long replyToMessageId) {
        this.messageId = messageId;
        this.senderUserId = senderUserId;
        this.senderFirstName = senderFirstName;
        this.senderLastName = senderLastName;
        this.senderBusinessId = senderBusinessId;
        this.receiverUserId = receiverUserId;
        this.receiverBusinessId = receiverBusinessId;
        this.messageContent = messageContent;
        this.sendDate = sendDate;
        this.replyToMessageId = replyToMessageId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(Long senderUserId) {
        this.senderUserId = senderUserId;
    }

    public String getSenderFirstName() {
        return senderFirstName;
    }

    public void setSenderFirstName(String senderFirstName) {
        this.senderFirstName = senderFirstName;
    }

    public String getSenderLastName() {
        return senderLastName;
    }

    public void setSenderLastName(String senderLastName) {
        this.senderLastName = senderLastName;
    }

    public Long getSenderBusinessId() {
        return senderBusinessId;
    }

    public void setSenderBusinessId(Long senderBusinessId) {
        this.senderBusinessId = senderBusinessId;
    }

    public Long getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(Long receiverUserId) {
        this.receiverUserId = receiverUserId;
    }

    public Long getReceiverBusinessId() {
        return receiverBusinessId;
    }

    public void setReceiverBusinessId(Long receiverBusinessId) {
        this.receiverBusinessId = receiverBusinessId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }

    public Long getReplyToMessageId() {
        return replyToMessageId;
    }

    public void setReplyToMessageId(Long replyToMessageId) {
        this.replyToMessageId = replyToMessageId;
    }
}
