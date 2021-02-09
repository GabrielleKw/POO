package com.example.demo.rest;

import com.example.demo.dto.ClienteRequest;
import com.example.demo.dto.ClienteResponse;
import com.example.demo.service.ClienteService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:2345", maxAge = 3600)
@RestController
public class ClienteApi {
    private final ClienteService clienteService;

    public ClienteApi(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    @PostMapping(value = "/api/v1/cliente", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody ClienteRequest clienteRequest){
        //CONFERE SE O ID EXISTE E CRIA
        if(clienteRequest.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(clienteService.salvarCliente(clienteRequest));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clienteService.salvarCliente(clienteRequest));

    }
    @GetMapping(value = "/api/v1/cliente", produces = "application/json")
    public ResponseEntity<List<ClienteResponse>> findAll(Pageable pageable){
        return ResponseEntity.ok(clienteService.buscarTodos(pageable));
    }

    @GetMapping(value = "/api/v1/cliente/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable Long id){
        var clienteResponse = clienteService.buscarById(id);
        if(clienteResponse == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(clienteResponse);
    }
}
