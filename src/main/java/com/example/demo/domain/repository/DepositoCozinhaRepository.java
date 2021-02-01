package com.example.demo.domain.repository;

import com.example.demo.domain.model.DepositoCozinha;
import com.example.demo.domain.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositoCozinhaRepository extends JpaRepository<DepositoCozinha, Long> {
}
