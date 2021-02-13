package com.example.demo.rest;

import com.example.demo.service.VendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendaApi {
    private final VendaService vendaService;

    public VendaApi(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @PostMapping("/api/v1/venda/{idProduto}/{idVendaFinal}/{idPrecoCusto}/{idPrecoVenda}")
    public ResponseEntity<?> criarVenda(@PathVariable Long idProduto, @PathVariable Long idVendaFinal, @PathVariable Long idPrecoCusto, @PathVariable Long idPrecoVenda){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(vendaService.novaVenda(idProduto, idVendaFinal, idPrecoCusto, idPrecoVenda));
        }catch(RuntimeException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getLocalizedMessage());
        }
    }

    @PostMapping("/api/v1/venda/{vendaId}/concluir")
    public ResponseEntity<?> concluirEmprestimo(@PathVariable Long vendaId){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(vendaService.concluirVenda(vendaId));
        }catch(RuntimeException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getLocalizedMessage());
        }
    }
}
