package com.example.demo.domain.repository;

import com.example.demo.domain.model.DepositoCozinha;
import com.example.demo.domain.model.ListaProdutos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListaProdutosRepository extends JpaRepository<ListaProdutos, Long> {
}
