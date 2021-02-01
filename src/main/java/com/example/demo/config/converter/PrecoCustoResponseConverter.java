package com.example.demo.config.converter;

import com.example.demo.domain.model.PrecoCusto;
import com.example.demo.dto.PrecoCustoResponse;
import com.example.demo.dto.ProdutoResponse;

public class PrecoCustoResponseConverter {
    public PrecoCustoResponse fromPrecoCusto(PrecoCusto precoCusto){
        var produto = precoCusto.getProduto();
        return PrecoCustoResponse.builder()
                .id(precoCusto.getId())
                .valor(precoCusto.getValor())
                .produto(ProdutoResponse.builder()
                        .id(produto.getId())
                        .nome(produto.getNome())
                        .build())
                .build();
    }
}
