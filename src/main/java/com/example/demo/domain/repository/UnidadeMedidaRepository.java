package com.example.demo.domain.repository;

import com.example.demo.domain.model.DepositoCozinha;
import com.example.demo.domain.model.UnidadeMedida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadeMedidaRepository extends JpaRepository<UnidadeMedida, Long> {
}
