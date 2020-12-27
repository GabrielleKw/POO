package com.example.demo.dto;

import com.example.demo.domain.model.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ProdutoRequest {
    private Long id;
    private String nome;
    private StatusEnum status;
    private LocalDateTime dataCadastro;
    private TributacaoRequest tributacao;
    private FornecedorRequest fornecedor;
    private FabricanteRequest fabricante;
    private UnidadeMedidaRequest unidadeMedida;
}
