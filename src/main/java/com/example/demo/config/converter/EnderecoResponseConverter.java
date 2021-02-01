package com.example.demo.config.converter;

import com.example.demo.domain.model.Endereco;
import com.example.demo.domain.model.StatusEnum;
import com.example.demo.dto.CidadeResponse;
import com.example.demo.dto.EnderecoResponse;
import com.example.demo.dto.EstadoResponse;

public class EnderecoResponseConverter {
    public EnderecoResponse fromEndereco(Endereco endereco){
        var cidade = endereco.getCidade();
        var estado = cidade.getEstado();
        return EnderecoResponse.builder()
                .id(endereco.getId())
                .bairro(endereco.getBairro())
                .cep(endereco.getCep())
                .rua(endereco.getRua())
                .complemento(endereco.getComplemento())
                .numero(endereco.getNumero())
                .cidade(cidade == null?null: CidadeResponse.builder()
                        .id(cidade.getId())
                        .nome(cidade.getNome())
                        .estado(estado == null?null: EstadoResponse.builder()
                                .id(estado.getId())
                                .nome(estado.getNome())
                                .sigla(estado.getSigla())
                                .build())
                        .build())
                .build();
    }
}
