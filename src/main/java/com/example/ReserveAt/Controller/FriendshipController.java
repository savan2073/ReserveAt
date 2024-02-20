package com.example.ReserveAt.Controller;

import com.example.ReserveAt.Dto.AddFriendRequest;
import com.example.ReserveAt.Dto.UserDTO;
import com.example.ReserveAt.Model.User;
import com.example.ReserveAt.Repository.UserRepository;
import com.example.ReserveAt.Service.FriendshipService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/friendship")
public class FriendshipController {
    @Autowired
    private FriendshipService friendshipService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addFriend(@RequestBody AddFriendRequest addFriendRequest, Principal principal) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

        friendshipService.addFriend(user.getUserId(), addFriendRequest.getFriendFirstName(), addFriendRequest.getFriendLastName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/my-friends")
    public ResponseEntity<List<UserDTO>> getMyFriends(Principal principal) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

        List<User> friends = friendshipService.getFriends(user.getUserId());

        List<UserDTO> friendsDTO = friends.stream()
                .map(friend -> new UserDTO(friend.getUserId(), friend.getFirstName(), friend.getLastName(), friend.getEmail(), null, null))
                .collect(Collectors.toList());

        return ResponseEntity.ok(friendsDTO);
    }
}
