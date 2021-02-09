package com.example.demo.service;

import com.example.demo.config.converter.DepositoVendaResponseConverter;
import com.example.demo.domain.model.*;
import com.example.demo.domain.repository.DepositoVendaRepository;
import com.example.demo.dto.DepositoCozinhaResponse;
import com.example.demo.dto.DepositoVendaRequest;
import com.example.demo.dto.DepositoVendaResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepositoVendaService {
    private final DepositoVendaRepository depositoVendaRepository;
    private final DepositoVendaResponseConverter depositoVendaResponseConverter;

    public DepositoVendaService(DepositoVendaRepository depositoVendaRepository, DepositoVendaResponseConverter depositoVendaResponseConverter) {
        this.depositoVendaRepository = depositoVendaRepository;
        this.depositoVendaResponseConverter = depositoVendaResponseConverter;
    }

    public DepositoVendaResponse salvarDepositoVenda(DepositoVendaRequest depositoVendaRequest){
        var produtoRequest = depositoVendaRequest.getProduto();
        var tributacaoRequest = depositoVendaRequest.getTributacao();
        var unidadeMedidaRequest = depositoVendaRequest.getUnidadeMedida();

        var depositoVenda = DepositoVenda.builder()
                .id(depositoVendaRequest.getId())
                .produto(Produto.builder()
                        .id(produtoRequest.getId())
                        .build())
                .dataCadastro(LocalDateTime.now())
                .quantidade(depositoVendaRequest.getQuantidade())
                .status(StatusEnum.ATIVO)
                .tributacao(Tributacao.builder()
                        .id(tributacaoRequest.getId())
                        .build())
                .unidadeMedida(UnidadeMedida.builder()
                        .id(unidadeMedidaRequest.getId())
                        .build())
                .build();
        depositoVendaRepository.save(depositoVenda);
        return depositoVendaResponseConverter.fromDepositoVenda(depositoVenda);
    }

    public List<DepositoVendaResponse> buscarTodos(Pageable pageable){
        return depositoVendaRepository.findAll(pageable).stream()
                .map(depositoVenda -> depositoVendaResponseConverter.fromDepositoVenda(depositoVenda)).collect(Collectors.toList());
    }

    public DepositoVendaResponse buscarById(Long id){
        return depositoVendaRepository.findById(id).map(depositoVenda -> depositoVendaResponseConverter.fromDepositoVenda(depositoVenda)).orElse(null);
    }
}
