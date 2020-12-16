package com.example.demo.domain.repository;

import com.example.demo.domain.model.DepositoCozinha;
import com.example.demo.domain.model.PrecoVenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrecoVendaRepository extends JpaRepository<PrecoVenda, Long> {
}
