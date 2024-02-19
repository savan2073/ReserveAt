package com.example.ReserveAt.Repository;

import com.example.ReserveAt.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByReceiverUserUserId(Long userId);
    List<Message> findByReceiverBusinessBusinessId(Long businessId);
}
