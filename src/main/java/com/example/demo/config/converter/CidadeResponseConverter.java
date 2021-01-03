package com.example.demo.config.converter;

import com.example.demo.domain.model.Cidade;
import com.example.demo.dto.CidadeResponse;
import com.example.demo.dto.EstadoResponse;
import org.springframework.stereotype.Component;

@Component
public class CidadeResponseConverter {
    public CidadeResponse fromCidade(Cidade cidade){
        var estado = cidade.getEstado();
        return CidadeResponse.builder()
                .id(cidade.getId())
                .nome(cidade.getNome())
                .estado(estado == null?null: EstadoResponse.builder()
                        .id(estado.getId())
                        .nome(estado.getNome())
                        .sigla(estado.getSigla())
                        .build())
                .build();
    }
}
