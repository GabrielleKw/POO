package com.example.demo.service;

import com.example.demo.config.converter.ListaProdutosResponseConverter;
import com.example.demo.domain.model.*;
import com.example.demo.domain.repository.ListaProdutosRepository;
import com.example.demo.dto.FuncionarioRequest;
import com.example.demo.dto.FuncionarioResponse;
import com.example.demo.dto.ListaProdutosRequest;
import com.example.demo.dto.ListaProdutosResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListaProdutosService {
    private final ListaProdutosRepository listaProdutosRepository;
    private final ListaProdutosResponseConverter listaProdutosResponseConverter;

    public ListaProdutosService(ListaProdutosRepository listaProdutosRepository, ListaProdutosResponseConverter listaProdutosResponseConverter) {
        this.listaProdutosRepository = listaProdutosRepository;
        this.listaProdutosResponseConverter = listaProdutosResponseConverter;
    }

    public ListaProdutosResponse salvarListaProdutos(ListaProdutosRequest listaProdutosRequest) {
        var produtoRequest = listaProdutosRequest.getProduto();
        var precoCustoRequest = listaProdutosRequest.getPrecoCusto();
        var vendaFinalRequest = listaProdutosRequest.getVendaFinal();

        var listaProdutos = ListaProdutos.builder()
                .id(listaProdutosRequest.getId())
                .quantidade(listaProdutosRequest.getQuantidade())
                .desconto(listaProdutosRequest.getDesconto())
                .produto(Produto.builder()
                        .id(produtoRequest.getId())
                        .build())
                .precoCusto(PrecoCusto.builder()
                        .id(precoCustoRequest.getId())
                        .build())
                .vendaFinal(VendaFinal.builder()
                        .id(vendaFinalRequest.getId())
                        .build())
                .dataCadastro(LocalDateTime.now())
                .status(StatusEnum.ATIVO)
                .build();
        listaProdutosRepository.save(listaProdutos);
        return listaProdutosResponseConverter.fromListaProdutos(listaProdutos);
    }

    public List<ListaProdutosResponse> buscarTodos(Pageable pageable) {
        return listaProdutosRepository.findAll(pageable).stream()
                .map(listaProdutos -> listaProdutosResponseConverter.fromListaProdutos(listaProdutos)).collect(Collectors.toList());
    }

    public ListaProdutosResponse buscarById(Long id) {
        return listaProdutosRepository.findById(id).map(listaProdutos -> listaProdutosResponseConverter.fromListaProdutos(listaProdutos)).orElse(null);
    }
}
