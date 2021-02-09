package com.example.demo.rest;

import com.example.demo.dto.EstadoRequest;
import com.example.demo.dto.EstadoResponse;
import com.example.demo.service.EstadoService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:2345", maxAge = 3600)
@RestController
public class EstadoApi {
    private final EstadoService estadoService;

    public EstadoApi(EstadoService estadoService) {
        this.estadoService = estadoService;
    }
    @PostMapping(value = "/api/v1/estado", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody EstadoRequest estadoRequest){
        //CONFERE SE O ID EXISTE E CRIA
        if(estadoRequest.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(estadoService.salvarEstado(estadoRequest));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(estadoService.salvarEstado(estadoRequest));

    }
    @GetMapping(value = "/api/v1/estado", produces = "application/json")
    public ResponseEntity<List<EstadoResponse>> findAll(Pageable pageable){
        return ResponseEntity.ok(estadoService.buscarTodos(pageable));
    }

    @GetMapping(value = "/api/v1/estado/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable Long id){
        var estadoResponse = estadoService.buscarById(id);
        if(estadoResponse == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(estadoResponse);
    }
}
