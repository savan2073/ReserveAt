package com.example.ReserveAt.Repository;

import com.example.ReserveAt.Model.Friendship;
import com.example.ReserveAt.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    boolean existsByUserAndFriend(User user, User friend);

    List<Friendship> findAllByUserUserId(Long userId);
}
