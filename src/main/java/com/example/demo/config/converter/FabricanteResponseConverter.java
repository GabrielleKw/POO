package com.example.demo.config.converter;

import com.example.demo.domain.model.Fabricante;
import com.example.demo.dto.FabricanteResponse;

public class FabricanteResponseConverter {
    public FabricanteResponse fromFabricante(Fabricante fabricante){
        return FabricanteResponse.builder()
                .id(fabricante.getId())
                .nome(fabricante.getNome())
                .build();
    }
}
