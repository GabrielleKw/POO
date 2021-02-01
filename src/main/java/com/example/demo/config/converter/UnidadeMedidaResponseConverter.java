package com.example.demo.config.converter;

import com.example.demo.domain.model.UnidadeMedida;
import com.example.demo.dto.UnidadeMedidaResponse;

public class UnidadeMedidaResponseConverter {
    public UnidadeMedidaResponse fromUnidadeMedida(UnidadeMedida unidadeMedida){
        return UnidadeMedidaResponse.builder()
                .id(unidadeMedida.getId())
                .abreviacao(unidadeMedida.getAbreviacao())
                .nome(unidadeMedida.getNome())
                .build();
    }
}
