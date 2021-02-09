package com.example.demo.service;

import com.example.demo.config.converter.ProdutoResponseConverter;
import com.example.demo.domain.model.*;
import com.example.demo.domain.repository.ProdutoRepository;
import com.example.demo.dto.PrecoVendaRequest;
import com.example.demo.dto.PrecoVendaResponse;
import com.example.demo.dto.ProdutoRequest;
import com.example.demo.dto.ProdutoResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final ProdutoResponseConverter produtoResponseConverter;

    public ProdutoService(ProdutoRepository produtoRepository, ProdutoResponseConverter produtoResponseConverter) {
        this.produtoRepository = produtoRepository;
        this.produtoResponseConverter = produtoResponseConverter;
    }


    public ProdutoResponse salvarProduto(ProdutoRequest produtoRequest) {
        var tributacao = produtoRequest.getTributacao();
        var fornecedor = produtoRequest.getFornecedor();
        var um = produtoRequest.getUnidadeMedida();
        var fabricante = produtoRequest.getFabricante();
        var produto = Produto.builder()
                .id(produtoRequest.getId())
                .nome(produtoRequest.getNome())
                .tributacao(Tributacao.builder()
                        .id(tributacao.getId())
                        .build())
                .fornecedor(Fornecedor.builder()
                        .id(fornecedor.getId())
                        .build())
                .unidadeMedida(UnidadeMedida.builder()
                        .id(um.getId())
                        .build())
                .fabricante(Fabricante.builder()
                        .id(fabricante.getId())
                        .build())
                .dataCadastro(LocalDateTime.now())
                .status(StatusEnum.ATIVO)
                .build();
        produtoRepository.save(produto);
        return produtoResponseConverter.fromProduto(produto);
    }

    public List<ProdutoResponse> buscarTodos(Pageable pageable) {
        return produtoRepository.findAll(pageable).stream()
                .map(produto -> produtoResponseConverter.fromProduto(produto)).collect(Collectors.toList());
    }

    public ProdutoResponse buscarById(Long id) {
        return produtoRepository.findById(id).map(produto -> produtoResponseConverter.fromProduto(produto)).orElse(null);
    }
}
