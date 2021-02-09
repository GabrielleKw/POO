package com.example.demo.service;

import com.example.demo.config.converter.TributacaoResponseConverter;
import com.example.demo.domain.model.*;
import com.example.demo.domain.repository.TributacaoRepository;
import com.example.demo.dto.ProdutoRequest;
import com.example.demo.dto.ProdutoResponse;
import com.example.demo.dto.TributacaoRequest;
import com.example.demo.dto.TributacaoResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TributacaoService {
    private final TributacaoRepository tributacaoRepository;
    private final TributacaoResponseConverter tributacaoResponseConverter;

    public TributacaoService(TributacaoRepository tributacaoRepository, TributacaoResponseConverter tributacaoResponseConverter) {
        this.tributacaoRepository = tributacaoRepository;
        this.tributacaoResponseConverter = tributacaoResponseConverter;
    }

    public TributacaoResponse salvarTriutacao(TributacaoRequest tributacaoRequest) {
        var tributacao = Tributacao.builder()
                .id(tributacaoRequest.getId())
                .descricao(tributacaoRequest.getDescricao())
                .familia(tributacaoRequest.getFamilia())
                .valor(tributacaoRequest.getValor())
                .status(StatusEnum.ATIVO)
                .build();
        tributacaoRepository.save(tributacao);
        return tributacaoResponseConverter.fromTributacao(tributacao);
    }

    public List<TributacaoResponse> buscarTodos(Pageable pageable) {
        return tributacaoRepository.findAll(pageable).stream()
                .map(tributacao -> tributacaoResponseConverter.fromTributacao(tributacao)).collect(Collectors.toList());
    }

    public TributacaoResponse buscarById(Long id) {
        return tributacaoRepository.findById(id).map(tributacao -> tributacaoResponseConverter.fromTributacao(tributacao)).orElse(null);
    }
}
