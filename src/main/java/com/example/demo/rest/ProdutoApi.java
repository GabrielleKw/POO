package com.example.demo.rest;

import com.example.demo.dto.PrecoVendaRequest;
import com.example.demo.dto.PrecoVendaResponse;
import com.example.demo.dto.ProdutoRequest;
import com.example.demo.dto.ProdutoResponse;
import com.example.demo.service.ProdutoService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:2345", maxAge = 3600)
@RestController
public class ProdutoApi {
    private final ProdutoService produtoService;

    public ProdutoApi(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping(value = "/api/v1/produto", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody ProdutoRequest produtoRequest){
        //CONFERE SE O ID EXISTE E CRIA
        if(produtoRequest.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(produtoService.salvarProduto(produtoRequest));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produtoService.salvarProduto(produtoRequest));

    }
    @GetMapping(value = "/api/v1/produto", produces = "application/json")
    public ResponseEntity<List<ProdutoResponse>> findAll(Pageable pageable){
        return ResponseEntity.ok(produtoService.buscarTodos(pageable));
    }

    @GetMapping(value = "/api/v1/produto/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable Long id){
        var produtoResponse = produtoService.buscarById(id);
        if(produtoResponse == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(produtoResponse);
    }
}
