package com.example.demo.service;

import com.example.demo.config.converter.PrecoVendaResponseConverter;
import com.example.demo.domain.model.PrecoCusto;
import com.example.demo.domain.model.PrecoVenda;
import com.example.demo.domain.model.Produto;
import com.example.demo.domain.model.StatusEnum;
import com.example.demo.domain.repository.PrecoVendaRepository;
import com.example.demo.dto.PrecoCustoRequest;
import com.example.demo.dto.PrecoCustoResponse;
import com.example.demo.dto.PrecoVendaRequest;
import com.example.demo.dto.PrecoVendaResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrecoVendaService {
    private final PrecoVendaRepository precoVendaRepository;
    private final PrecoVendaResponseConverter precoVendaResponseConverter;

    public PrecoVendaService(PrecoVendaRepository precoVendaRepository, PrecoVendaResponseConverter precoVendaResponseConverter) {
        this.precoVendaRepository = precoVendaRepository;
        this.precoVendaResponseConverter = precoVendaResponseConverter;
    }

    public PrecoVendaResponse salvarPrecoVenda(PrecoVendaRequest precoVendaRequest) {
        var produto = precoVendaRequest.getProduto();
        var precoVenda = PrecoVenda.builder()
                .id(precoVendaRequest.getId())
                .valor(precoVendaRequest.getValor())
                .produto(Produto.builder()
                        .id(produto.getId())
                        .build())
                .dataCadastro(LocalDateTime.now())
                .status(StatusEnum.ATIVO)
                .build();
        precoVendaRepository.save(precoVenda);
        return precoVendaResponseConverter.fromPrecoVenda(precoVenda);
    }

    public List<PrecoVendaResponse> buscarTodos(Pageable pageable) {
        return precoVendaRepository.findAll(pageable).stream()
                .map(precoVenda -> precoVendaResponseConverter.fromPrecoVenda(precoVenda)).collect(Collectors.toList());
    }

    public PrecoVendaResponse buscarById(Long id) {
        return precoVendaRepository.findById(id).map(precoVenda -> precoVendaResponseConverter.fromPrecoVenda(precoVenda)).orElse(null);
    }
}
