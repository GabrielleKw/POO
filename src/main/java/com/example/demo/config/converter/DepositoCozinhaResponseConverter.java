package com.example.demo.config.converter;

import com.example.demo.domain.model.DepositoCozinha;
import com.example.demo.dto.DepositoCozinhaResponse;
import com.example.demo.dto.ProdutoResponse;
import com.example.demo.dto.UnidadeMedidaResponse;
import org.springframework.stereotype.Component;

@Component
public class DepositoCozinhaResponseConverter {
    public DepositoCozinhaResponse fromDepositoCozinhaResponse(DepositoCozinha depositoCozinha){
        var produto = depositoCozinha.getProduto();
        var tributacao = depositoCozinha.getTributacao();
        var unidadeMedida = depositoCozinha.getUnidadeMedida();
        return DepositoCozinhaResponse.builder()
                .id(depositoCozinha.getId())
                .quantidade(depositoCozinha.getQuantidade())
                .produto(ProdutoResponse.builder()
                        .id(produto.getId())
                        .nome(produto.getNome())
                        .build())
                //rever as colunas que faltam e apagar este comentário
                .unidadeMedida(UnidadeMedidaResponse.builder()
                        .id(unidadeMedida.getId())
                        .abreviacao(unidadeMedida.getAbreviacao())
                        .build())
                .build();
    }
}
