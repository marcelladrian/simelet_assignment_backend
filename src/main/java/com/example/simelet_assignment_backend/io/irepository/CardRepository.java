package com.example.simelet_assignment_backend.io.irepository;

import com.example.simelet_assignment_backend.io.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CardRepository extends JpaRepository<CardEntity, Long> {
    List<CardEntity> findAllByUser(UserEntity userEntity);

    CardEntity findByCardid(String cardId);
}
