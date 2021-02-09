package com.example.demo.service;

import com.example.demo.config.converter.VendaFinalResponseConverter;
import com.example.demo.domain.model.*;
import com.example.demo.domain.repository.VendaFinalRepository;
import com.example.demo.dto.UnidadeMedidaRequest;
import com.example.demo.dto.UnidadeMedidaResponse;
import com.example.demo.dto.VendaFinalRequest;
import com.example.demo.dto.VendaFinalResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendaFinalService {
    private final VendaFinalRepository vendaFinalRepository;
    private final VendaFinalResponseConverter vendaFinalResponseConverter;

    public VendaFinalService(VendaFinalRepository vendaFinalRepository, VendaFinalResponseConverter vendaFinalResponseConverter) {
        this.vendaFinalRepository = vendaFinalRepository;
        this.vendaFinalResponseConverter = vendaFinalResponseConverter;
    }

    public VendaFinalResponse salvarVendaFinal(VendaFinalRequest vendaFinalRequest) {
        var pagamento = vendaFinalRequest.getPagamento();
        var cliente = vendaFinalRequest.getCliente();
        var funcionario = vendaFinalRequest.getFuncionario();
        var vendaFinal = VendaFinal.builder()
                .id(vendaFinalRequest.getId())
                .precoFinal(vendaFinalRequest.getPrecoFinal())
                .desconto(vendaFinalRequest.getDesconto())
                .pagamento(Pagamento.builder()
                        .id(pagamento.getId())
                        .build())
                .cliente(Cliente.builder()
                        .id(cliente.getId())
                        .build())
                .funcionario(Funcionario.builder()
                        .id(funcionario.getId())
                        .build())
                .dataCadastro(LocalDateTime.now())
                .status(StatusEnum.ATIVO)
                .build();
        vendaFinalRepository.save(vendaFinal);
        return vendaFinalResponseConverter.fromVendaFinal(vendaFinal);
    }

    public List<VendaFinalResponse> buscarTodos(Pageable pageable) {
        return vendaFinalRepository.findAll(pageable).stream()
                .map(vendaFinal -> vendaFinalResponseConverter.fromVendaFinal(vendaFinal)).collect(Collectors.toList());
    }

    public VendaFinalResponse buscarById(Long id) {
        return vendaFinalRepository.findById(id).map(vendaFinal -> vendaFinalResponseConverter.fromVendaFinal(vendaFinal)).orElse(null);
    }
}
