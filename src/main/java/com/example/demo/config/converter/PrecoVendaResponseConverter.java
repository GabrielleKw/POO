package com.example.demo.config.converter;

import com.example.demo.domain.model.PrecoVenda;
import com.example.demo.dto.PrecoCustoResponse;
import com.example.demo.dto.PrecoVendaResponse;
import com.example.demo.dto.ProdutoResponse;
import org.springframework.stereotype.Component;

@Component
public class PrecoVendaResponseConverter {
    public PrecoVendaResponse fromPrecoVenda(PrecoVenda precoVenda ){
        var produto = precoVenda.getProduto();
        return PrecoVendaResponse.builder()
                .id(precoVenda.getId())
                .valor(precoVenda.getValor())
                .produto(ProdutoResponse.builder()
                        .id(produto.getId())
                        .nome(produto.getNome())
                        .build())
                .build();
    }
}
