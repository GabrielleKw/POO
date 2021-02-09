package com.example.demo.rest;

import com.example.demo.dto.UnidadeMedidaRequest;
import com.example.demo.dto.UnidadeMedidaResponse;
import com.example.demo.dto.VendaFinalRequest;
import com.example.demo.dto.VendaFinalResponse;
import com.example.demo.service.VendaFinalService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:2345", maxAge = 3600)
@RestController
public class VendaFinalApi {
    private final VendaFinalService vendaFinalService;

    public VendaFinalApi(VendaFinalService vendaFinalService) {
        this.vendaFinalService = vendaFinalService;
    }

    @PostMapping(value = "/api/v1/vendaFinal", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody VendaFinalRequest vendaFinalRequest){
        //CONFERE SE O ID EXISTE E CRIA
        if(vendaFinalRequest.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(vendaFinalService.salvarVendaFinal(vendaFinalRequest));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vendaFinalService.salvarVendaFinal(vendaFinalRequest));

    }
    @GetMapping(value = "/api/v1/vendaFinal", produces = "application/json")
    public ResponseEntity<List<VendaFinalResponse>> findAll(Pageable pageable){
        return ResponseEntity.ok(vendaFinalService.buscarTodos(pageable));
    }

    @GetMapping(value = "/api/v1/vendaFinal/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable Long id){
        var vendaFinalResponse = vendaFinalService.buscarById(id);
        if(vendaFinalResponse == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(vendaFinalResponse);
    }
}
