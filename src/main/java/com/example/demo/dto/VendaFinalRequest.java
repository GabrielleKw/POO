package com.example.demo.dto;

import com.example.demo.domain.model.Cliente;
import com.example.demo.domain.model.Funcionario;
import com.example.demo.domain.model.Pagamento;
import com.example.demo.domain.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
public class VendaFinalRequest {
    private Long id;
    private BigDecimal precoFinal;
    private BigDecimal desconto;
    private StatusEnum status;
    private LocalDateTime dataCadastro;
    private PagamentoRequest pagamento;
    private ClienteRequest cliente;
    private FuncionarioRequest funcionario;
}
