package com.example.demo.rest;

import com.example.demo.dto.CidadeRequest;
import com.example.demo.dto.CidadeResponse;
import com.example.demo.service.CidadeService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:2345", maxAge = 3600)
@RestController
public class CidadeApi {
    private final CidadeService cidadeService;

    public CidadeApi(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }
    @PostMapping(value = "/api/v1/cidade", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody CidadeRequest cidadeRequest){
        //CONFERE SE O ID EXISTE E CRIA
        if(cidadeRequest.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(cidadeService.salvarCidade(cidadeRequest));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cidadeService.salvarCidade(cidadeRequest));

    }
    @GetMapping(value = "/api/v1/cidade", produces = "application/json")
    public ResponseEntity<List<CidadeResponse>> findAll(Pageable pageable){
        return ResponseEntity.ok(cidadeService.buscarTodos(pageable));
    }

    @GetMapping(value = "/api/v1/cidade/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable Long id){
        var cidadeResponse = cidadeService.buscarById(id);
        if(cidadeResponse == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(cidadeResponse);
    }
}
