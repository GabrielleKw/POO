package com.example.demo.rest;

import com.example.demo.dto.DepositoCozinhaRequest;
import com.example.demo.dto.DepositoCozinhaResponse;
import com.example.demo.service.DepositoCozinhaService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:2345", maxAge = 3600)
@RestController
public class DepositoCozinhaApi {
    private final DepositoCozinhaService depositoCozinhaService;

    public DepositoCozinhaApi(DepositoCozinhaService depositoCozinhaService) {
        this.depositoCozinhaService = depositoCozinhaService;
    }
    @PostMapping(value = "/api/v1/depositoCozinha", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody DepositoCozinhaRequest depositoCozinhaRequest){
        //CONFERE SE O ID EXISTE E CRIA
        if(depositoCozinhaRequest.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(depositoCozinhaService.salvarDepositoCozinha(depositoCozinhaRequest));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(depositoCozinhaService.salvarDepositoCozinha(depositoCozinhaRequest));

    }
    @GetMapping(value = "/api/v1/depositoCozinha", produces = "application/json")
    public ResponseEntity<List<DepositoCozinhaResponse>> findAll(Pageable pageable){
        return ResponseEntity.ok(depositoCozinhaService.buscarTodos(pageable));
    }

    @GetMapping(value = "/api/v1/depositoCozinha/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable Long id){
        var depositoCozinhaResponse = depositoCozinhaService.buscarById(id);
        if(depositoCozinhaResponse == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(depositoCozinhaResponse);
    }
}
