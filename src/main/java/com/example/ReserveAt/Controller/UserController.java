package com.example.ReserveAt.Controller;
import com.example.ReserveAt.Dto.LoginDTO;
import com.example.ReserveAt.Dto.UserDTO;
import com.example.ReserveAt.Model.User;
import com.example.ReserveAt.Repository.UserRepository;
import com.example.ReserveAt.Response.LoginMessage;
import com.example.ReserveAt.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.PrimitiveIterator;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public String saveUser(@RequestBody UserDTO userDTO) {
        String id = userService.addUser(userDTO);
        System.out.println(userDTO);
        return id;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        LoginMessage loginResponse = userService.loginUser(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable Long userId, Principal principal) throws AccessDeniedException {
        //pobieranie nazwy użytkownika z Principal(token JWT)
        String email = principal.getName();

        //pobieranie danych zalogowanego użytkownika
        User currentUser = userRepository.findByEmail(email);

        //sprawdzanie czy zalogowany użytkownika ma dostęp do podanego profilu
        if (currentUser != null && currentUser.getUserId().equals(userId)) {
            UserDTO userDto = userService.getUserById(userId);
            return ResponseEntity.ok(userDto);
        } else {
            throw new AccessDeniedException("Brak dostępu do podanego profilu");
        }
    }
}