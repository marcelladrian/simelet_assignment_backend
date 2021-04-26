package com.example.simelet_assignment_backend.ui.controller;

import com.example.simelet_assignment_backend.service.iservice.IServiceUsers;
import com.example.simelet_assignment_backend.shared.dto.UsersDTO;
import com.example.simelet_assignment_backend.ui.model.request.UsersRequest;
import com.example.simelet_assignment_backend.ui.model.response.UsersResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    private final IServiceUsers iServiceUsers;

    public UsersController(IServiceUsers iServiceUsers) {
        this.iServiceUsers = iServiceUsers;
    }

    @GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UsersResponse getUserByUserId(@PathVariable String userId){
        ModelMapper mapper = new ModelMapper();
        UsersDTO getUser = iServiceUsers.getUserByUserId(userId);
        if (getUser == null)
            return null;
        return mapper.map(getUser, UsersResponse.class);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public UsersResponse registerUser(@RequestBody UsersRequest usersRequest){
        ModelMapper mapper = new ModelMapper();
        UsersDTO userDTO = mapper.map(usersRequest, UsersDTO.class);
        UsersDTO createdUser = iServiceUsers.addNewUsers(userDTO);

        UsersResponse value = mapper.map(createdUser, UsersResponse.class);
        return value;
    }

    @PostMapping(path = "/login", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public UsersResponse loginUser(@RequestBody UsersRequest usersRequest){
        ModelMapper mapper = new ModelMapper();
        UsersDTO userDTO = mapper.map(usersRequest, UsersDTO.class);
        UsersDTO createdUser = iServiceUsers.addNewUsers(userDTO);

        UsersResponse value = mapper.map(createdUser, UsersResponse.class);
        return value;
    }
}
