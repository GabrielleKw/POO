package com.example.demo.config.converter;

import com.example.demo.domain.model.ListaProdutos;
import com.example.demo.dto.*;
import org.springframework.stereotype.Component;

@Component
public class ListaProdutosResponseConverter {
    public ListaProdutosResponse fromListaProdutos(ListaProdutos listaProdutos){
        var  produto = listaProdutos.getProduto();
        var vendaFinal = listaProdutos.getVendaFinal();
        var precoCusto = listaProdutos.getPrecoCusto();
        var um = produto.getUnidadeMedida();

        return ListaProdutosResponse.builder()
                .id(listaProdutos.getId())
                .desconto(listaProdutos.getDesconto())
                .produto(ProdutoResponse.builder()
                        .id(produto.getId())
                        .nome(produto.getNome())
                        .unidadeMedida(UnidadeMedidaResponse.builder()
                                .id(um.getId())
                                .nome(um.getNome())
                                .abreviacao(um.getAbreviacao())
                                .build())
                        .build())
                .precoCusto(PrecoCustoResponse.builder()
                        .id(precoCusto.getId())
                        .valor(precoCusto.getValor())
                        .build())
                .quantidade(listaProdutos.getQuantidade())
                .vendaFinal(VendaFinalResponse.builder()
                        .id(vendaFinal.getId())
                        .build())
                .build();
    }
}
