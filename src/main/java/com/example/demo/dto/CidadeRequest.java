package com.example.demo.dto;

import com.example.demo.domain.model.Estado;
import com.example.demo.domain.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class CidadeRequest {
    private Long id;
    private String nome;
    private StatusEnum status;
    private LocalDateTime dataCadastro;
    private EstadoRequest estado;

}
