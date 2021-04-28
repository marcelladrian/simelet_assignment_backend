package com.example.simelet_assignment_backend.io.irepository;

import com.example.simelet_assignment_backend.io.entity.CardEntity;
import com.example.simelet_assignment_backend.io.entity.TransactionsEntity;
import com.example.simelet_assignment_backend.io.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionsRepository extends JpaRepository<TransactionsEntity, Long> {

    List<TransactionsEntity> findAllByCardEntity(CardEntity cardEntity);

    TransactionsEntity findByTransactionsId(String transactionsid);
}
