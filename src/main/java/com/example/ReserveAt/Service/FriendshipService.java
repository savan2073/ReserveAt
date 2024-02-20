package com.example.ReserveAt.Service;

import com.example.ReserveAt.Model.User;

import java.util.List;

public interface FriendshipService {

    void addFriend(Long userId, String friendFirstName, String friendLastName);

    List<User> getFriends(Long userId);
}
