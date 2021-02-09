package com.example.demo.service;

import com.example.demo.config.converter.UnidadeMedidaResponseConverter;
import com.example.demo.domain.model.StatusEnum;
import com.example.demo.domain.model.Tributacao;
import com.example.demo.domain.model.UnidadeMedida;
import com.example.demo.domain.repository.UnidadeMedidaRepository;
import com.example.demo.dto.TributacaoRequest;
import com.example.demo.dto.TributacaoResponse;
import com.example.demo.dto.UnidadeMedidaRequest;
import com.example.demo.dto.UnidadeMedidaResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnidadeMedidaService {
    private final UnidadeMedidaRepository unidadeMedidaRepository;
    private final UnidadeMedidaResponseConverter unidadeMedidaResponseConverter;

    public UnidadeMedidaService(UnidadeMedidaRepository unidadeMedidaRepository, UnidadeMedidaResponseConverter unidadeMedidaResponseConverter) {
        this.unidadeMedidaRepository = unidadeMedidaRepository;
        this.unidadeMedidaResponseConverter = unidadeMedidaResponseConverter;
    }

    public UnidadeMedidaResponse salvarUnidadeMedida(UnidadeMedidaRequest unidadeMedidaRequest) {
        var unidadeMedida = UnidadeMedida.builder()
                .id(unidadeMedidaRequest.getId())
                .nome(unidadeMedidaRequest.getNome())
                .abreviacao(unidadeMedidaRequest.getAbreviacao())
                .dataCadastro(LocalDateTime.now())
                .status(StatusEnum.ATIVO)
                .build();
        unidadeMedidaRepository.save(unidadeMedida);
        return unidadeMedidaResponseConverter.fromUnidadeMedida(unidadeMedida);
    }

    public List<UnidadeMedidaResponse> buscarTodos(Pageable pageable) {
        return unidadeMedidaRepository.findAll(pageable).stream()
                .map(unidadeMedida -> unidadeMedidaResponseConverter.fromUnidadeMedida(unidadeMedida)).collect(Collectors.toList());
    }

    public UnidadeMedidaResponse buscarById(Long id) {
        return unidadeMedidaRepository.findById(id).map(unidadeMedida -> unidadeMedidaResponseConverter.fromUnidadeMedida(unidadeMedida)).orElse(null);
    }
}
