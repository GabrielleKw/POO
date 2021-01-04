package com.example.demo.dto;

import com.example.demo.domain.model.Produto;
import com.example.demo.domain.model.StatusEnum;
import com.example.demo.domain.model.Tributacao;
import com.example.demo.domain.model.UnidadeMedida;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class DepositoCozinhaRequest {
    private Long id;
    private Long quantidade;
    private ProdutoRequest produto;
    private TributacaoRequest tributacao;
    private UnidadeMedidaRequest unidadeMedida;
    private StatusEnum status;
    private LocalDateTime dataCadastro;

}
