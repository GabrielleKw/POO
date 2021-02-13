package com.example.demo.config.converter;

import com.example.demo.domain.model.Estado;
import com.example.demo.dto.EstadoResponse;
import org.springframework.stereotype.Component;

@Component
public class EstadoResponseConverter {
    public EstadoResponse fromEstado(Estado estado){
        return EstadoResponse.builder()
                .id(estado.getId())
                .nome(estado.getNome())
                .sigla(estado.getSigla())
                .status(estado.getStatus())
                .build();
    }
}
