package com.example.simelet_assignment_backend.service.iservice;

import com.example.simelet_assignment_backend.shared.dto.UsersDTO;

public interface IServiceUsers {
    UsersDTO getUserByUserId(String userId);

    UsersDTO addNewUsers(UsersDTO userDTO);

    UsersDTO editUser(String userId, UsersDTO usersDTO);

    UsersDTO deleteUser(String userId);

    UsersDTO login(UsersDTO usersDTO);
}
