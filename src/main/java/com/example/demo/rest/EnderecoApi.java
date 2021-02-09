package com.example.demo.rest;

import com.example.demo.dto.EnderecoRequest;
import com.example.demo.dto.EnderecoResponse;
import com.example.demo.service.EnderecoService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:2345", maxAge = 3600)
@RestController
public class EnderecoApi {
    private final EnderecoService enderecoService;

    public EnderecoApi(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }
    @PostMapping(value = "/api/v1/endereco", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody EnderecoRequest enderecoRequest){
        //CONFERE SE O ID EXISTE E CRIA
        if(enderecoRequest.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(enderecoService.salvarEndereco(enderecoRequest));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(enderecoService.salvarEndereco(enderecoRequest));

    }
    @GetMapping(value = "/api/v1/endereco", produces = "application/json")
    public ResponseEntity<List<EnderecoResponse>> findAll(Pageable pageable){
        return ResponseEntity.ok(enderecoService.buscarTodos(pageable));
    }

    @GetMapping(value = "/api/v1/endereco/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable Long id){
        var enderecoResponse = enderecoService.buscarById(id);
        if(enderecoResponse == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(enderecoResponse);
    }
}
