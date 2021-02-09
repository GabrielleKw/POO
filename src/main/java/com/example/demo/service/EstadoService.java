package com.example.demo.service;

import com.example.demo.config.converter.EstadoResponseConverter;
import com.example.demo.domain.model.Estado;
import com.example.demo.domain.model.StatusEnum;
import com.example.demo.domain.repository.EstadoRepository;
import com.example.demo.dto.EnderecoRequest;
import com.example.demo.dto.EnderecoResponse;
import com.example.demo.dto.EstadoRequest;
import com.example.demo.dto.EstadoResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadoService {
    private final EstadoRepository estadoRepository;
    private final EstadoResponseConverter estadoResponseConverter;

    public EstadoService(EstadoRepository estadoRepository, EstadoResponseConverter estadoResponseConverter) {
        this.estadoRepository = estadoRepository;
        this.estadoResponseConverter = estadoResponseConverter;
    }


    public EstadoResponse salvarEstado(EstadoRequest estadoRequest){
       var estado = Estado.builder()
               .id(estadoRequest.getId())
               .nome(estadoRequest.getNome())
               .sigla(estadoRequest.getSigla())
               .dataCadastro(LocalDateTime.now())
               .status(StatusEnum.ATIVO)
               .build();

        estadoRepository.save(estado);
        return estadoResponseConverter.fromEstado(estado);
    }

    public List<EstadoResponse> buscarTodos(Pageable pageable){
        return estadoRepository.findAll(pageable).stream()
                .map(estado -> estadoResponseConverter.fromEstado(estado)).collect(Collectors.toList());
    }

    public EstadoResponse buscarById(Long id){
        return estadoRepository.findById(id).map(estado -> estadoResponseConverter.fromEstado(estado)).orElse(null);
    }
}
