package com.example.simelet_assignment_backend.service.impl;

import com.example.simelet_assignment_backend.io.entity.UsersEntity;
import com.example.simelet_assignment_backend.io.irepository.UsersRepository;
import com.example.simelet_assignment_backend.service.iservice.IServiceUsers;
import com.example.simelet_assignment_backend.shared.dto.UsersDTO;
import com.example.simelet_assignment_backend.shared.utils.GenerateRandomPublicId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements IServiceUsers {
    private final UsersRepository usersRepository;
    private final GenerateRandomPublicId generateRandomPublicId;

    public UsersServiceImpl(UsersRepository usersRepository, GenerateRandomPublicId generateRandomPublicId) {
        this.usersRepository = usersRepository;
        this.generateRandomPublicId = generateRandomPublicId;
    }

    @Override
    public UsersDTO getUserByUserId(String userId) {
        ModelMapper mapper = new ModelMapper();
        UsersEntity getUser = usersRepository.findByUserId(userId);
        if (getUser == null)
            return null;
        return mapper.map(getUser, UsersDTO.class);
    }

    @Override
    public UsersDTO addNewUsers(UsersDTO userDTO) {
        ModelMapper mapper = new ModelMapper();
        userDTO.setUserId(generateRandomPublicId.generateUserId(30));

        UsersEntity usersEntity = mapper.map(userDTO, UsersEntity.class);
        UsersEntity storedData = usersRepository.save(usersEntity);

        UsersDTO value = mapper.map(storedData, UsersDTO.class);
        return value;
    }
}
