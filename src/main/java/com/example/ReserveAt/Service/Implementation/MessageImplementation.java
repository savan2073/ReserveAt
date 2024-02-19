package com.example.ReserveAt.Service.Implementation;

import com.example.ReserveAt.Dto.MessageDTO;
import com.example.ReserveAt.Model.Business;
import com.example.ReserveAt.Model.Message;
import com.example.ReserveAt.Model.User;
import com.example.ReserveAt.Repository.BusinessRepository;
import com.example.ReserveAt.Repository.MessageRepository;
import com.example.ReserveAt.Repository.UserRepository;
import com.example.ReserveAt.Service.MessageService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageImplementation implements MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BusinessRepository businessRepository;


    @Override
    public MessageDTO sendMessage(MessageDTO messageDTO) {
        Message message = new Message();
        if (messageDTO.getSenderUserId() != null) {
            User senderUser = userRepository.findById(messageDTO.getSenderUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + messageDTO.getSenderUserId()));
            message.setSenderUser(senderUser);
        }
        if (messageDTO.getSenderBusinessId() != null) {
            Business senderBusiness = businessRepository.findById(messageDTO.getSenderBusinessId())
                    .orElseThrow(() -> new EntityNotFoundException("Business not found with id: " + messageDTO.getSenderBusinessId()));
            message.setSenderBusiness(senderBusiness);
        }

        if (messageDTO.getReceiverUserId() != null) {
            User receiverUser = userRepository.findById(messageDTO.getReceiverUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + messageDTO.getReceiverUserId()));
            message.setReceiverUser(receiverUser);
        }

        if (messageDTO.getReceiverBusinessId() != null) {
            Business receiverBusiness = businessRepository.findById(messageDTO.getReceiverBusinessId())
                    .orElseThrow(() -> new EntityNotFoundException("Business not found with id: " + messageDTO.getReceiverBusinessId()));
            message.setReceiverBusiness(receiverBusiness);
        }
        if (messageDTO.getReplyToMessageId() != null) {
            message.setReplyToMessageId(messageDTO.getReplyToMessageId());
        }

        message.setMessageContent(messageDTO.getMessageContent());
        message.setSendDate(LocalDateTime.now());

        Message savedMessage = messageRepository.save(message);
        return convertToDTO(savedMessage);
    }

    @Override
    public List<MessageDTO> getMessagesForUser(Long userId) {
        List<Message> messages = messageRepository.findByReceiverUserUserId(userId);
        return messages.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<MessageDTO> getMessagesForBusiness(Long businessId) {
        List<Message> messages = messageRepository.findByReceiverBusinessBusinessId(businessId);
        return messages.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private MessageDTO convertToDTO(Message message) {
        MessageDTO messageDTO = new MessageDTO();

        messageDTO.setMessageId(message.getMessageId());

        if (message.getSenderUser() != null) {
            messageDTO.setSenderUserId(message.getSenderUser().getUserId());

        }
        if (message.getSenderBusiness() != null) {
            messageDTO.setSenderBusinessId(message.getSenderBusiness().getBusinessId());
        }
        if (message.getReceiverUser() != null) {
            messageDTO.setReceiverUserId(message.getReceiverUser().getUserId());
        }
        if (message.getReceiverBusiness() != null) {
            messageDTO.setReceiverBusinessId(message.getReceiverBusiness().getBusinessId());
        }


        messageDTO.setMessageContent(message.getMessageContent());
        messageDTO.setSendDate(message.getSendDate());

        return messageDTO;
    }
}
