package com.example.demo.dto;

import com.example.demo.domain.model.PrecoCusto;
import com.example.demo.domain.model.Produto;
import com.example.demo.domain.model.StatusEnum;
import com.example.demo.domain.model.VendaFinal;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
public class ListaProdutosRequest {
    private Long id;
    private Long quantidade;
    private BigDecimal desconto;
    private StatusEnum status;
    private LocalDateTime dataCadastro;
    private ProdutoRequest produto;
    private VendaFinalRequest vendaFinal;
    private PrecoCustoRequest precoCusto;

}
