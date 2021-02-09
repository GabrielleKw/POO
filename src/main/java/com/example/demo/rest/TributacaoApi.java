package com.example.demo.rest;

import com.example.demo.dto.TributacaoRequest;
import com.example.demo.dto.TributacaoResponse;
import com.example.demo.service.TributacaoService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:2345", maxAge = 3600)
@RestController
public class TributacaoApi {
    private final TributacaoService tributacaoService;

    public TributacaoApi(TributacaoService tributacaoService) {
        this.tributacaoService = tributacaoService;
    }

    @PostMapping(value = "/api/v1/tributacao", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody TributacaoRequest tributacaoRequest){
        //CONFERE SE O ID EXISTE E CRIA
        if(tributacaoRequest.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(tributacaoService.salvarTriutacao(tributacaoRequest));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tributacaoService.salvarTriutacao(tributacaoRequest));

    }
    @GetMapping(value = "/api/v1/tributacao", produces = "application/json")
    public ResponseEntity<List<TributacaoResponse>> findAll(Pageable pageable){
        return ResponseEntity.ok(tributacaoService.buscarTodos(pageable));
    }

    @GetMapping(value = "/api/v1/tributacao/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable Long id){
        var tributacaoResponse = tributacaoService.buscarById(id);
        if(tributacaoResponse == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(tributacaoResponse);
    }
}
