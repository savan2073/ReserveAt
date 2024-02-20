package com.example.ReserveAt.Service.Implementation;

import com.example.ReserveAt.Model.Friendship;
import com.example.ReserveAt.Model.User;
import com.example.ReserveAt.Repository.FriendshipRepository;
import com.example.ReserveAt.Repository.UserRepository;
import com.example.ReserveAt.Service.FriendshipService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendshipImplementation implements FriendshipService {

    @Autowired
    private FriendshipRepository friendshipRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public void addFriend(Long userId, String friendFirstName, String friendLastName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        List<User> potentialFriends = userRepository.findByFirstNameAndLastName(friendFirstName, friendLastName);

        if (potentialFriends.size() != 1) {
            throw new EntityNotFoundException("Friend not found or multiple users found");
        }
        User friend = potentialFriends.get(0);
        //zablokowanie możliwości dodania samego siebie do znajomych
        if(friend.getUserId().equals(userId)) {
            throw new IllegalArgumentException("Cannot add yourself as a friend");
        }
        //zablokowanie dodawania dla znajomości jeżeli już istnieje
        if (friendshipRepository.existsByUserAndFriend(user, friend)) {
            throw new IllegalArgumentException("Friendship already exists");
        }

        Friendship friendship = new Friendship(null, user, friend);
        friendshipRepository.save(friendship);
    }

    @Override
    public List<User> getFriends(Long userId) {
        return friendshipRepository.findAllByUserUserId(userId)
                .stream()
                .map(Friendship::getFriend)
                .collect(Collectors.toList());
    }
}
