package com.example.demo.service;

import com.example.demo.config.converter.FabricanteResponseConverter;
import com.example.demo.domain.model.Estado;
import com.example.demo.domain.model.Fabricante;
import com.example.demo.domain.model.StatusEnum;
import com.example.demo.domain.repository.FabricanteRepository;
import com.example.demo.dto.EstadoRequest;
import com.example.demo.dto.EstadoResponse;
import com.example.demo.dto.FabricanteRequest;
import com.example.demo.dto.FabricanteResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FabricanteService {
    private final FabricanteRepository fabricanteRepository;
    private final FabricanteResponseConverter fabricanteResponseConverter;

    public FabricanteService(FabricanteRepository fabricanteRepository, FabricanteResponseConverter fabricanteResponseConverter) {
        this.fabricanteRepository = fabricanteRepository;
        this.fabricanteResponseConverter = fabricanteResponseConverter;
    }

    public FabricanteResponse salvarFabricante(FabricanteRequest fabricanteRequest){
       var fabricante = Fabricante.builder()
               .id(fabricanteRequest.getId())
               .nome(fabricanteRequest.getNome())
               .dataCadastro(LocalDateTime.now())
               .status(StatusEnum.ATIVO)
               .build();

        fabricanteRepository.save(fabricante);
        return fabricanteResponseConverter.fromFabricante(fabricante);
    }

    public List<FabricanteResponse> buscarTodos(Pageable pageable){
        return fabricanteRepository.findAll(pageable).stream()
                .map(fabricante -> fabricanteResponseConverter.fromFabricante(fabricante)).collect(Collectors.toList());
    }

    public FabricanteResponse buscarById(Long id){
        return fabricanteRepository.findById(id).map(fabricante -> fabricanteResponseConverter.fromFabricante(fabricante)).orElse(null);
    }
}
