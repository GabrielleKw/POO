package com.example.demo.service;

import com.example.demo.config.converter.PagamentoResponseConverter;
import com.example.demo.domain.model.Pagamento;
import com.example.demo.domain.model.StatusEnum;
import com.example.demo.domain.repository.PagamentoRepository;
import com.example.demo.dto.ListaProdutosRequest;
import com.example.demo.dto.ListaProdutosResponse;
import com.example.demo.dto.PagamentoRequest;
import com.example.demo.dto.PagamentoResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagamentoService {
    private final PagamentoRepository pagamentoRepository;
    private final PagamentoResponseConverter pagamentoResponseConverter;

    public PagamentoService(PagamentoRepository pagamentoRepository, PagamentoResponseConverter pagamentoResponseConverter) {
        this.pagamentoRepository = pagamentoRepository;
        this.pagamentoResponseConverter = pagamentoResponseConverter;
    }

    public PagamentoResponse salvarPagamento(PagamentoRequest pagamentoRequest) {
        var pagamento = Pagamento.builder()
                .id(pagamentoRequest.getId())
                .tipo(pagamentoRequest.getTipo())
                .descricao(pagamentoRequest.getDescricao())
                .dataCadastro(LocalDateTime.now())
                .status(StatusEnum.ATIVO)
                .build();
        pagamentoRepository.save(pagamento);
        return pagamentoResponseConverter.fromPagamento(pagamento);
    }

    public List<PagamentoResponse> buscarTodos(Pageable pageable) {
        return pagamentoRepository.findAll(pageable).stream()
                .map(pagamento -> pagamentoResponseConverter.fromPagamento(pagamento)).collect(Collectors.toList());
    }

    public PagamentoResponse buscarById(Long id) {
        return pagamentoRepository.findById(id).map(pagamento -> pagamentoResponseConverter.fromPagamento(pagamento)).orElse(null);
    }
}
