package com.example.demo.domain.repository;

import com.example.demo.domain.model.DepositoCozinha;
import com.example.demo.domain.model.PrecoCusto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrecoCustoRepository extends JpaRepository<PrecoCusto, Long> {
}
