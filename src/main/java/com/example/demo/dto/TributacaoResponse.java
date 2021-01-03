package com.example.demo.dto;

import com.example.demo.domain.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
public class TributacaoResponse {
    private Long id;
    private String familia;
    private String descricao;
    private BigDecimal valor;
    private StatusEnum status;
}
