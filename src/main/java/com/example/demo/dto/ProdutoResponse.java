package com.example.demo.dto;

import com.example.demo.domain.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ProdutoResponse {
    private Long id;
    private String nome;
    private StatusEnum status;
    private TributacaoResponse tributacao;
    private FornecedorResponse fornecedor;
    private FabricanteResponse fabricante;
    private UnidadeMedidaResponse unidadeMedida;
}
