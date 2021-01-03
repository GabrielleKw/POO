package com.example.demo.dto;

import com.example.demo.domain.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
public class VendaFinalResponse {
    private Long id;
    private BigDecimal precoFinal;
    private BigDecimal desconto;
    private StatusEnum status;
    private PagamentoResponse pagamento;
    private ClienteResponse cliente;
    private FuncionarioResponse funcionario;
}
