package com.example.demo.rest;

import com.example.demo.dto.ClienteRequest;
import com.example.demo.dto.ClienteResponse;
import com.example.demo.dto.DepositoVendaRequest;
import com.example.demo.dto.DepositoVendaResponse;
import com.example.demo.service.ClienteService;
import com.example.demo.service.DepositoVendaService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:2345", maxAge = 3600)
@RestController
public class DepositoVendaApi {
    private final DepositoVendaService depositoVendaService;

    public DepositoVendaApi(DepositoVendaService depositoVendaService) {
        this.depositoVendaService = depositoVendaService;
    }
    @PostMapping(value = "/api/v1/depositoVenda", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody DepositoVendaRequest depositoVendaRequest){
        //CONFERE SE O ID EXISTE E CRIA
        if(depositoVendaRequest.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(depositoVendaService.salvarDepositoVenda(depositoVendaRequest));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(depositoVendaService.salvarDepositoVenda(depositoVendaRequest));

    }
    @GetMapping(value = "/api/v1/depositoVenda", produces = "application/json")
    public ResponseEntity<List<DepositoVendaResponse>> findAll(Pageable pageable){
        return ResponseEntity.ok(depositoVendaService.buscarTodos(pageable));
    }

    @GetMapping(value = "/api/v1/depositoVenda/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable Long id){
        var depositoVendaResponse = depositoVendaService.buscarById(id);
        if(depositoVendaResponse == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(depositoVendaResponse);
    }
}
