package com.example.demo.rest;

import com.example.demo.dto.FabricanteRequest;
import com.example.demo.dto.FabricanteResponse;
import com.example.demo.dto.FornecedorRequest;
import com.example.demo.dto.FornecedorResponse;
import com.example.demo.service.FabricanteService;
import com.example.demo.service.FornecedorService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:2345", maxAge = 3600)
@RestController
public class FornecedorApi {
    private final FornecedorService fornecedorService;

    public FornecedorApi(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }
    @PostMapping(value = "/api/v1/fornecedor", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody FornecedorRequest fornecedorRequest){
        //CONFERE SE O ID EXISTE E CRIA
        if(fornecedorRequest.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(fornecedorService.salvarFornecedor(fornecedorRequest));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fornecedorService.salvarFornecedor(fornecedorRequest));

    }
    @GetMapping(value = "/api/v1/fornecedor", produces = "application/json")
    public ResponseEntity<List<FornecedorResponse>> findAll(Pageable pageable){
        return ResponseEntity.ok(fornecedorService.buscarTodos(pageable));
    }

    @GetMapping(value = "/api/v1/fornecedor/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable Long id){
        var fornecedorResponse = fornecedorService.buscarById(id);
        if(fornecedorResponse == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(fornecedorResponse);
    }
}
