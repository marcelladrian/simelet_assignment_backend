package com.example.simelet_assignment_backend.io.irepository;

import com.example.simelet_assignment_backend.io.entity.BalanceEntity;
import com.example.simelet_assignment_backend.io.entity.CardEntity;
import com.example.simelet_assignment_backend.io.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CardRepository extends JpaRepository<CardEntity, Long> {
    List<CardEntity> findAllByUser(UsersEntity userEntity);

    CardEntity findByUserAndCardid(UsersEntity usersEntity, String cardId);
    CardEntity findByCardid(String cardId);
    CardEntity findByUserAndCardidAndBalanceEntity(UsersEntity usersEntity, String cardId, BalanceEntity balanceEntity);
}
