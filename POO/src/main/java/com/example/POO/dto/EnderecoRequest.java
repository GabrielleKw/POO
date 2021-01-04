package com.example.demo.dto;

import com.example.demo.domain.model.Cidade;
import com.example.demo.domain.model.StatusEnum;

import java.time.LocalDateTime;

public class EnderecoRequest {
    private Long id;
    private String rua;
    private String bairro;
    private String cep;
    private Long numero;
    private String complemento;
    private StatusEnum status;
    private LocalDateTime dataCadastro;
    private CidadeRequest cidade;


}
