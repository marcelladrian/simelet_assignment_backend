package com.example.simelet_assignment_backend.io.irepository;

import com.example.simelet_assignment_backend.io.entity.BalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<BalanceEntity, Long> {
    BalanceEntity findByBalanceid(String BalanceId);
}
