package com.example.demo.service;

import com.example.demo.config.converter.EnderecoResponseConverter;
import com.example.demo.domain.model.Cidade;
import com.example.demo.domain.model.Endereco;
import com.example.demo.domain.model.StatusEnum;
import com.example.demo.domain.repository.EnderecoRepository;
import com.example.demo.dto.DepositoVendaRequest;
import com.example.demo.dto.DepositoVendaResponse;
import com.example.demo.dto.EnderecoRequest;
import com.example.demo.dto.EnderecoResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final EnderecoResponseConverter enderecoResponseConverter;

    public EnderecoService(EnderecoRepository enderecoRepository, EnderecoResponseConverter enderecoResponseConverter) {
        this.enderecoRepository = enderecoRepository;
        this.enderecoResponseConverter = enderecoResponseConverter;
    }

    public EnderecoResponse salvarEndereco(EnderecoRequest enderecoRequest){
        var cidade = enderecoRequest.getCidade();
        var estado = cidade.getEstado();

        var endereco = Endereco.builder()
                .id(enderecoRequest.getId())
                .rua(enderecoRequest.getRua())
                .numero(enderecoRequest.getNumero())
                .bairro(enderecoRequest.getBairro())
                .complemento(enderecoRequest.getComplemento())
                .cep(enderecoRequest.getCep())
                .dataCadastro(LocalDateTime.now())
                .status(StatusEnum.ATIVO)
                .cidade(Cidade.builder()
                        .id(cidade.getId())
                        .build())
                .build();

        enderecoRepository.save(endereco);
        return enderecoResponseConverter.fromEndereco(endereco);
    }

    public List<EnderecoResponse> buscarTodos(Pageable pageable){
        return enderecoRepository.findAll(pageable).stream()
                .map(endereco -> enderecoResponseConverter.fromEndereco(endereco)).collect(Collectors.toList());
    }

    public EnderecoResponse buscarById(Long id){
        return enderecoRepository.findById(id).map(endereco -> enderecoResponseConverter.fromEndereco(endereco)).orElse(null);
    }
}
