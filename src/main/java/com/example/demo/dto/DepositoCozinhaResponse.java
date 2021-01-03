package com.example.demo.dto;

import com.example.demo.domain.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class DepositoCozinhaResponse {
    private Long id;
    private Long quantidade;
    private ProdutoResponse produto;
    private TributacaoResponse tributacao;
    private UnidadeMedidaResponse unidadeMedida;
    private StatusEnum status;

}
