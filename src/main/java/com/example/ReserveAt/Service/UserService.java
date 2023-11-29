package com.example.ReserveAt.Service;
import com.example.ReserveAt.Dto.LoginDTO;
import com.example.ReserveAt.Dto.UserDTO;
import com.example.ReserveAt.Model.User;
import com.example.ReserveAt.Repository.UserRepository;
import com.example.ReserveAt.Response.LoginMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {
    String addUser(UserDTO userDTO);

    LoginMessage loginUser(LoginDTO loginDTO);

}