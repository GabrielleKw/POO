package com.example.demo.domain.repository;

import com.example.demo.domain.model.DepositoCozinha;
import com.example.demo.domain.model.DepositoVenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositoVendaRepository extends JpaRepository<DepositoVenda, Long> {
}
