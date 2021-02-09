package com.example.demo.service;

import com.example.demo.config.converter.CidadeResponseConverter;
import com.example.demo.config.converter.DepositoCozinhaResponseConverter;
import com.example.demo.domain.model.*;
import com.example.demo.domain.repository.CidadeRepository;
import com.example.demo.domain.repository.DepositoCozinhaRepository;
import com.example.demo.dto.DepositoCozinhaRequest;
import com.example.demo.dto.DepositoCozinhaResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepositoCozinhaService {
    private final DepositoCozinhaRepository depositoCozinhaRepository;
    private final DepositoCozinhaResponseConverter depositoCozinhaResponseConverter;

    public DepositoCozinhaService(DepositoCozinhaRepository depositoCozinhaRepository, DepositoCozinhaResponseConverter depositoCozinhaResponseConverter) {
        this.depositoCozinhaRepository = depositoCozinhaRepository;
        this.depositoCozinhaResponseConverter = depositoCozinhaResponseConverter;
    }
    public DepositoCozinhaResponse salvarDepositoCozinha(DepositoCozinhaRequest depositoCozinhaRequest){
        var produtoRequest = depositoCozinhaRequest.getProduto();
        var tributacaoRequest = depositoCozinhaRequest.getTributacao();
        var unidadeMedidaRequest = depositoCozinhaRequest.getUnidadeMedida();

        var depositoCozinha = DepositoCozinha.builder()
                .id(depositoCozinhaRequest.getId())
                .produto(Produto.builder()
                        .id(produtoRequest.getId())
                        .build())
                .dataCadastro(LocalDateTime.now())
                .quantidade(depositoCozinhaRequest.getQuantidade())
                .status(StatusEnum.ATIVO)
                .tributacao(Tributacao.builder()
                        .id(tributacaoRequest.getId())
                        .build())
                .unidadeMedida(UnidadeMedida.builder()
                        .id(unidadeMedidaRequest.getId())
                        .build())
                .build();
        depositoCozinhaRepository.save(depositoCozinha);
        return depositoCozinhaResponseConverter.fromDepositoCozinha(depositoCozinha);
    }

    public List<DepositoCozinhaResponse> buscarTodos(Pageable pageable){
        return depositoCozinhaRepository.findAll(pageable).stream()
                .map(depositoCozinha -> depositoCozinhaResponseConverter.fromDepositoCozinha(depositoCozinha)).collect(Collectors.toList());
    }

    public DepositoCozinhaResponse buscarById(Long id){
        return depositoCozinhaRepository.findById(id).map(depositoCozinha -> depositoCozinhaResponseConverter.fromDepositoCozinha(depositoCozinha)).orElse(null);
    }
}
