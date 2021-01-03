package com.example.demo.dto;

import com.example.demo.domain.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
public class ListaProdutosResponse {
    private Long id;
    private Long quantidade;
    private BigDecimal desconto;
    private StatusEnum status;
    private ProdutoResponse produto;
    private VendaFinalResponse vendaFinal;
    private PrecoCustoResponse precoCusto;

}
