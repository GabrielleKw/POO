package com.example.demo.domain.repository;

import com.example.demo.domain.model.DepositoCozinha;
import com.example.demo.domain.model.VendaFinal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaFinalRepository extends JpaRepository<VendaFinal, Long> {
}
