package com.example.demo.rest;

import com.example.demo.dto.PrecoCustoRequest;
import com.example.demo.dto.PrecoCustoResponse;
import com.example.demo.dto.PrecoVendaRequest;
import com.example.demo.dto.PrecoVendaResponse;
import com.example.demo.service.PrecoVendaService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:2345", maxAge = 3600)
@RestController
public class PrecoVendaApi {
    private final PrecoVendaService precoVendaService;

    public PrecoVendaApi(PrecoVendaService precoVendaService) {
        this.precoVendaService = precoVendaService;
    }

    @PostMapping(value = "/api/v1/precoVenda", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody PrecoVendaRequest precoVendaRequest){
        //CONFERE SE O ID EXISTE E CRIA
        if(precoVendaRequest.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(precoVendaService.salvarPrecoVenda(precoVendaRequest));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(precoVendaService.salvarPrecoVenda(precoVendaRequest));

    }
    @GetMapping(value = "/api/v1/precoVenda", produces = "application/json")
    public ResponseEntity<List<PrecoVendaResponse>> findAll(Pageable pageable){
        return ResponseEntity.ok(precoVendaService.buscarTodos(pageable));
    }

    @GetMapping(value = "/api/v1/precoVenda/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable Long id){
        var precoVendaResponse = precoVendaService.buscarById(id);
        if(precoVendaResponse == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(precoVendaResponse);
    }
}
