package com.example.demo.rest;

import com.example.demo.dto.ListaProdutosRequest;
import com.example.demo.dto.ListaProdutosResponse;
import com.example.demo.dto.PagamentoRequest;
import com.example.demo.dto.PagamentoResponse;
import com.example.demo.service.ListaProdutosService;
import com.example.demo.service.PagamentoService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:2345", maxAge = 3600)
@RestController
public class PagamentoApi {
    private final PagamentoService pagamentoService;

    public PagamentoApi(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping(value = "/api/v1/pagamento", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody PagamentoRequest pagamentoRequest){
        //CONFERE SE O ID EXISTE E CRIA
        if(pagamentoRequest.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(pagamentoService.salvarPagamento(pagamentoRequest));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pagamentoService.salvarPagamento(pagamentoRequest));

    }
    @GetMapping(value = "/api/v1/pagamento", produces = "application/json")
    public ResponseEntity<List<PagamentoResponse>> findAll(Pageable pageable){
        return ResponseEntity.ok(pagamentoService.buscarTodos(pageable));
    }

    @GetMapping(value = "/api/v1/pagamento/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable Long id){
        var pagamentoResponse = pagamentoService.buscarById(id);
        if(pagamentoResponse == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(pagamentoResponse);
    }
}
