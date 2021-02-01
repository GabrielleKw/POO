package com.example.demo.dto;

import com.example.demo.domain.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Builder
@Data
public class EnderecoResponse {
    private Long id;
    private String rua;
    private String bairro;
    private String cep;
    private Long numero;
    private String complemento;
    private StatusEnum status;
    private CidadeResponse cidade;


}
