package com.example.demo.domain.repository;

import com.example.demo.domain.model.DepositoCozinha;
import com.example.demo.domain.model.Tributacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TributacaoRepository extends JpaRepository<Tributacao, Long> {
}
