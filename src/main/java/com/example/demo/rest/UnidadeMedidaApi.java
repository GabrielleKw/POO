package com.example.demo.rest;

import com.example.demo.dto.TributacaoRequest;
import com.example.demo.dto.TributacaoResponse;
import com.example.demo.dto.UnidadeMedidaRequest;
import com.example.demo.dto.UnidadeMedidaResponse;
import com.example.demo.service.UnidadeMedidaService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:2345", maxAge = 3600)
@RestController
public class UnidadeMedidaApi {
    private final UnidadeMedidaService unidadeMedidaService;

    public UnidadeMedidaApi(UnidadeMedidaService unidadeMedidaService) {
        this.unidadeMedidaService = unidadeMedidaService;
    }

    @PostMapping(value = "/api/v1/unidadeMedida", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody UnidadeMedidaRequest unidadeMedidaRequest){
        //CONFERE SE O ID EXISTE E CRIA
        if(unidadeMedidaRequest.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(unidadeMedidaService.salvarUnidadeMedida(unidadeMedidaRequest));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(unidadeMedidaService.salvarUnidadeMedida(unidadeMedidaRequest));

    }
    @GetMapping(value = "/api/v1/unidadeMedida", produces = "application/json")
    public ResponseEntity<List<UnidadeMedidaResponse>> findAll(Pageable pageable){
        return ResponseEntity.ok(unidadeMedidaService.buscarTodos(pageable));
    }

    @GetMapping(value = "/api/v1/unidadeMedida/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable Long id){
        var unidadeMedidaResponse = unidadeMedidaService.buscarById(id);
        if(unidadeMedidaResponse == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(unidadeMedidaResponse);
    }
}
