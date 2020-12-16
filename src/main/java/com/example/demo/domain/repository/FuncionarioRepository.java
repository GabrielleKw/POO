package com.example.demo.domain.repository;

import com.example.demo.domain.model.DepositoCozinha;
import com.example.demo.domain.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
