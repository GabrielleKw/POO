package com.example.demo.rest;

import com.example.demo.dto.EstadoRequest;
import com.example.demo.dto.EstadoResponse;
import com.example.demo.dto.FabricanteRequest;
import com.example.demo.dto.FabricanteResponse;
import com.example.demo.service.EstadoService;
import com.example.demo.service.FabricanteService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:2345", maxAge = 3600)
@RestController
public class FabricanteApi {
    private final FabricanteService fabricanteService;

    public FabricanteApi(FabricanteService fabricanteService) {
        this.fabricanteService = fabricanteService;
    }
    @PostMapping(value = "/api/v1/fabricante", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody FabricanteRequest fabricanteRequest){
        //CONFERE SE O ID EXISTE E CRIA
        if(fabricanteRequest.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(fabricanteService.salvarFabricante(fabricanteRequest));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fabricanteService.salvarFabricante(fabricanteRequest));

    }
    @GetMapping(value = "/api/v1/fabricante", produces = "application/json")
    public ResponseEntity<List<FabricanteResponse>> findAll(Pageable pageable){
        return ResponseEntity.ok(fabricanteService.buscarTodos(pageable));
    }

    @GetMapping(value = "/api/v1/fabricante/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable Long id){
        var fabricanteResponse = fabricanteService.buscarById(id);
        if(fabricanteResponse == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(fabricanteResponse);
    }
}
