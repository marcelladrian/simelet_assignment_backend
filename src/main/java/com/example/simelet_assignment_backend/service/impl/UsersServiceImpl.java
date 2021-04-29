package com.example.simelet_assignment_backend.service.impl;

import com.example.simelet_assignment_backend.io.entity.UsersEntity;
import com.example.simelet_assignment_backend.io.irepository.UsersRepository;
import com.example.simelet_assignment_backend.service.iservice.IServiceUsers;
import com.example.simelet_assignment_backend.shared.dto.UsersDTO;
import com.example.simelet_assignment_backend.shared.utils.GenerateRandomPublicId;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsersServiceImpl implements IServiceUsers {
    private final UsersRepository usersRepository;
    private final GenerateRandomPublicId generateRandomPublicId;
    String pepper = "pepper";
    int iterations = 200000;
    int hashWidth = 256;
    private final Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder(pepper, iterations, hashWidth);

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
        usersEntity.setCreatedAt(LocalDateTime.now());

        pbkdf2PasswordEncoder.setEncodeHashAsBase64(true);
        String encodedPassword = pbkdf2PasswordEncoder.encode(usersEntity.getPassword());

        usersEntity.setPassword(encodedPassword);

        UsersEntity storedData = usersRepository.save(usersEntity);

        UsersDTO value = mapper.map(storedData, UsersDTO.class);
        return value;
    }

    @Override
    public UsersDTO login(UsersDTO usersDTO) {
        ModelMapper mapper = new ModelMapper();
        String userName = usersDTO.getUserName();

        UsersEntity usersEntity = usersRepository.findByUserName(userName);

        if (usersEntity == null || !usersEntity.getPassword().equals(usersDTO.getPassword()))
            return null;

        return mapper.map(usersEntity, UsersDTO.class);
    }

    @Override
    public UsersDTO editUser(String userId, UsersDTO usersDTO) {
        UsersEntity usersEntity = usersRepository.findByUserId(userId);

        usersEntity.setImage(usersDTO.getImage());
        usersEntity.setNoHp(usersDTO.getNoHp());
        usersEntity.setName(usersDTO.getName());
        usersEntity.setEmail(usersDTO.getEmail());
        pbkdf2PasswordEncoder.setEncodeHashAsBase64(true);
        String encodedPassword = pbkdf2PasswordEncoder.encode(usersEntity.getPassword());

        usersEntity.setPassword(encodedPassword);

        UsersEntity value = usersRepository.save(usersEntity);

        return new ModelMapper().map(value, UsersDTO.class);
    }

    @Override
    public UsersDTO deleteUser(String userId) {
        UsersEntity usersEntity = usersRepository.findByUserId(userId);

        usersEntity.setDeleted(true);

        UsersEntity value = usersRepository.save(usersEntity);

        return new ModelMapper().map(value, UsersDTO.class);
    }

}
