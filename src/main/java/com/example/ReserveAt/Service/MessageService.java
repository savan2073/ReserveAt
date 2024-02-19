package com.example.ReserveAt.Service;

import com.example.ReserveAt.Model.Message;
import com.example.ReserveAt.Dto.MessageDTO;

import java.util.List;

public interface MessageService {

    MessageDTO sendMessage(MessageDTO messageDTO);
    List<MessageDTO> getMessagesForUser(Long userId);
    List<MessageDTO> getMessagesForBusiness(Long businessId);
}
