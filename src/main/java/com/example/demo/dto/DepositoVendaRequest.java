package com.example.demo.dto;

import com.example.demo.domain.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class DepositoVendaRequest {
    private Long id;
    private Long quantidade;
    private ProdutoRequest produto;
    private TributacaoRequest tributacao;
    private UnidadeMedidaRequest unidadeMedida;
    private StatusEnum status;
    private LocalDateTime dataCadastro;

}
