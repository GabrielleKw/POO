package com.example.demo.config.converter;

import com.example.demo.domain.model.Produto;
import com.example.demo.dto.*;

public class ProdutoResponseConverter {
    public ProdutoResponse fromProduto(Produto produto){
        var tributacao = produto.getTributacao();
        var fornecedor = produto.getFornecedor();
        var fabricante = produto.getFabricante();
        var um = produto.getUnidadeMedida();
        return ProdutoResponse.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .unidadeMedida(UnidadeMedidaResponse.builder()
                        .id(um.getId())
                        .nome(um.getNome())
                        .abreviacao(um.getAbreviacao())
                        .build())
                .fabricante(FabricanteResponse.builder()
                        .id(fabricante.getId())
                        .nome(fabricante.getNome())
                        .build())
                .fornecedor(FornecedorResponse.builder()
                        .id(fornecedor.getId())
                        .nome(fornecedor.getNome())
                        .cpf(fornecedor.getCpf())
                        .cnpj(fornecedor.getCnpj())
                        .build())
                .tributacao(TributacaoResponse.builder()
                        .id(tributacao.getId())
                        .valor(tributacao.getValor())
                        .descricao(tributacao.getDescricao())
                        .build())
                .build();
    }
}
