package com.example.demo.dto;

import com.example.demo.domain.model.Produto;
import com.example.demo.domain.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
public class PrecoCustoRequest {
    private Long id;
    private BigDecimal valor;
    private StatusEnum status;
    private LocalDateTime dataCadastro;
    private ProdutoRequest produto;
}
