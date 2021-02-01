package com.example.demo.config.converter;

import com.example.demo.domain.model.DepositoCozinha;
import com.example.demo.domain.model.DepositoVenda;
import com.example.demo.dto.DepositoCozinhaResponse;
import com.example.demo.dto.DepositoVendaResponse;
import com.example.demo.dto.ProdutoResponse;
import com.example.demo.dto.UnidadeMedidaResponse;
import org.springframework.stereotype.Component;

@Component
public class DepositoVendaResponseConverter {
    public DepositoVendaResponse fromDepositoVendaResponse(DepositoVenda depositoVenda){
        var produto = depositoVenda.getProduto();
        var tributacao = depositoVenda.getTributacao();
        var unidadeMedida = depositoVenda.getUnidadeMedida();
        return DepositoVendaResponse.builder()
                .id(depositoVenda.getId())
                .quantidade(depositoVenda.getQuantidade())
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
