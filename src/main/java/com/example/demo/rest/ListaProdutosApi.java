package com.example.demo.rest;

import com.example.demo.dto.FuncionarioRequest;
import com.example.demo.dto.FuncionarioResponse;
import com.example.demo.dto.ListaProdutosRequest;
import com.example.demo.dto.ListaProdutosResponse;
import com.example.demo.service.ListaProdutosService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:2345", maxAge = 3600)
@RestController
public class ListaProdutosApi {
    private final ListaProdutosService listaProdutosService;

    public ListaProdutosApi(ListaProdutosService listaProdutosService) {
        this.listaProdutosService = listaProdutosService;
    }

    @PostMapping(value = "/api/v1/listaProdutos", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody ListaProdutosRequest listaProdutosRequest){
        //CONFERE SE O ID EXISTE E CRIA
        if(listaProdutosRequest.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(listaProdutosService.salvarListaProdutos(listaProdutosRequest));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(listaProdutosService.salvarListaProdutos(listaProdutosRequest));

    }
    @GetMapping(value = "/api/v1/listaProdutos", produces = "application/json")
    public ResponseEntity<List<ListaProdutosResponse>> findAll(Pageable pageable){
        return ResponseEntity.ok(listaProdutosService.buscarTodos(pageable));
    }

    @GetMapping(value = "/api/v1/listaProdutos/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable Long id){
        var listaProdutosResponse = listaProdutosService.buscarById(id);
        if(listaProdutosResponse == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(listaProdutosResponse);
    }
}
