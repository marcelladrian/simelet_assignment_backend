package com.example.simelet_assignment_backend.io.irepository;

import com.example.simelet_assignment_backend.io.entity.TransactionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<TransactionsEntity, Long> {

}
