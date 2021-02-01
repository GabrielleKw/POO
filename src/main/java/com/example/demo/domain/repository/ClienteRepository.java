package com.example.demo.domain.repository;

import com.example.demo.domain.model.Cliente;
import com.example.demo.domain.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
