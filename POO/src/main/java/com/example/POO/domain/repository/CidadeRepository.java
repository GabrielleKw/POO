package com.example.demo.domain.repository;

import com.example.demo.domain.model.Cidade;
import com.example.demo.domain.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
