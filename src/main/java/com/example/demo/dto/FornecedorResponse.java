package com.example.demo.dto;

import com.example.demo.domain.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class FornecedorResponse {
    private Long id;
    private String nome;
    private StatusEnum status;
    private String telefone;
    private String cpf;
    private String cnpj;
    private EnderecoResponse endereco;

}
