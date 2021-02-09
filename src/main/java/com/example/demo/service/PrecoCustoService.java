package com.example.demo.service;

import com.example.demo.config.converter.PrecoCustoResponseConverter;
import com.example.demo.domain.model.Pagamento;
import com.example.demo.domain.model.PrecoCusto;
import com.example.demo.domain.model.Produto;
import com.example.demo.domain.model.StatusEnum;
import com.example.demo.domain.repository.PrecoCustoRepository;
import com.example.demo.dto.PagamentoRequest;
import com.example.demo.dto.PagamentoResponse;
import com.example.demo.dto.PrecoCustoRequest;
import com.example.demo.dto.PrecoCustoResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrecoCustoService {
    private final PrecoCustoRepository precoCustoRepository;
    private final PrecoCustoResponseConverter precoCustoResponseConverter;

    public PrecoCustoService(PrecoCustoRepository precoCustoRepository, PrecoCustoResponseConverter precoCustoResponseConverter) {
        this.precoCustoRepository = precoCustoRepository;
        this.precoCustoResponseConverter = precoCustoResponseConverter;
    }

    public PrecoCustoResponse salvarPrecoCusto(PrecoCustoRequest precoCustoRequest) {
        var produto = precoCustoRequest.getProduto();
        var precoCusto = PrecoCusto.builder()
                .id(precoCustoRequest.getId())
                .valor(precoCustoRequest.getValor())
                .produto(Produto.builder()
                        .id(produto.getId())
                        .build())
                .dataCadastro(LocalDateTime.now())
                .status(StatusEnum.ATIVO)
                .build();
        precoCustoRepository.save(precoCusto);
        return precoCustoResponseConverter.fromPrecoCusto(precoCusto);
    }

    public List<PrecoCustoResponse> buscarTodos(Pageable pageable) {
        return precoCustoRepository.findAll(pageable).stream()
                .map(precoCusto -> precoCustoResponseConverter.fromPrecoCusto(precoCusto)).collect(Collectors.toList());
    }

    public PrecoCustoResponse buscarById(Long id) {
        return precoCustoRepository.findById(id).map(precoCusto -> precoCustoResponseConverter.fromPrecoCusto(precoCusto)).orElse(null);
    }
}
