package com.example.demo.service;

import com.example.demo.config.converter.CidadeResponseConverter;
import com.example.demo.domain.model.Cidade;
import com.example.demo.domain.model.Estado;
import com.example.demo.domain.model.StatusEnum;
import com.example.demo.domain.repository.CidadeRepository;
import com.example.demo.dto.CidadeRequest;
import com.example.demo.dto.CidadeResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CidadeService {
    private final CidadeRepository cidadeRepository;
    private final CidadeResponseConverter cidadeResponseConverter;

    public CidadeService(CidadeRepository cidadeRepository, CidadeResponseConverter cidadeResponseConverter) {
        this.cidadeRepository = cidadeRepository;
        this.cidadeResponseConverter = cidadeResponseConverter;
    }
    public CidadeResponse salvarCidade(CidadeRequest cidadeRequest){
        var estadoRequest =cidadeRequest.getEstado();
        var cidade = Cidade.builder()
                .id(cidadeRequest.getId())
                .nome(cidadeRequest.getNome())
                .status(StatusEnum.ATIVO)
                .dataCadastro(LocalDateTime.now())
                .estado(Estado.builder()
                        .id(estadoRequest.getId())
                        .build())
                .build();
        cidadeRepository.save(cidade);
        return cidadeResponseConverter.fromCidade(cidade);
    }

    public List<CidadeResponse> buscarTodos(Pageable pageable){
        return cidadeRepository.findAll(pageable).stream()
                .map(cidade -> cidadeResponseConverter.fromCidade(cidade)).collect(Collectors.toList());
    }

    public CidadeResponse buscarById(Long id){
        return cidadeRepository.findById(id).map(cidade -> cidadeResponseConverter.fromCidade(cidade)).orElse(null);
    }
}
