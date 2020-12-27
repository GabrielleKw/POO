package com.example.demo.dto;

import com.example.demo.domain.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class PagamentoRequest {
    private Long id;
    private String tipo;
    private String descricao;
    private StatusEnum status;
    private LocalDateTime dataCadastro;

}
