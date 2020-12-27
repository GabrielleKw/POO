package com.example.demo.dto;

import com.example.demo.domain.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class UnidadeMedidaRequest {
    private Long id;
    private String nome;
    private String abreviacao;
    private StatusEnum status;
    private LocalDateTime dataCadastro;

}
