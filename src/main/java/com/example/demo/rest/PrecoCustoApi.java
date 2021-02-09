package com.example.demo.rest;

import com.example.demo.dto.PagamentoRequest;
import com.example.demo.dto.PagamentoResponse;
import com.example.demo.dto.PrecoCustoRequest;
import com.example.demo.dto.PrecoCustoResponse;
import com.example.demo.service.PrecoCustoService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:2345", maxAge = 3600)
@RestController
public class PrecoCustoApi {
    private final PrecoCustoService precoCustoService;

    public PrecoCustoApi(PrecoCustoService precoCustoService) {
        this.precoCustoService = precoCustoService;
    }

    @PostMapping(value = "/api/v1/precoCusto", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody PrecoCustoRequest precoCustoRequest){
        //CONFERE SE O ID EXISTE E CRIA
        if(precoCustoRequest.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(precoCustoService.salvarPrecoCusto(precoCustoRequest));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(precoCustoService.salvarPrecoCusto(precoCustoRequest));

    }
    @GetMapping(value = "/api/v1/precoCusto", produces = "application/json")
    public ResponseEntity<List<PrecoCustoResponse>> findAll(Pageable pageable){
        return ResponseEntity.ok(precoCustoService.buscarTodos(pageable));
    }

    @GetMapping(value = "/api/v1/precoCusto/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable Long id){
        var precoCustoResponse = precoCustoService.buscarById(id);
        if(precoCustoResponse == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(precoCustoResponse);
    }
}
