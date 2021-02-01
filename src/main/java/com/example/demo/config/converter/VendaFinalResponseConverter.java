package com.example.demo.config.converter;

import com.example.demo.domain.model.VendaFinal;
import com.example.demo.dto.ClienteResponse;
import com.example.demo.dto.FuncionarioResponse;
import com.example.demo.dto.PagamentoResponse;
import com.example.demo.dto.VendaFinalResponse;

public class VendaFinalResponseConverter {
    public VendaFinalResponse fromVendaFinal(VendaFinal vendaFinal){
        var pagamento = vendaFinal.getPagamento();
        var cliente = vendaFinal.getCliente();
        var funcionario = vendaFinal.getFuncionario();

        return VendaFinalResponse.builder()
                .id(vendaFinal.getId())
                .desconto(vendaFinal.getDesconto())
                .precoFinal(vendaFinal.getPrecoFinal())
                .cliente(ClienteResponse.builder()
                        .id(cliente.getId())
                        .nome(cliente.getNome())
                        .build())
                .funcionario(FuncionarioResponse.builder()
                        .id(funcionario.getId())
                        .nome(funcionario.getNome())
                        .build())
                .pagamento(PagamentoResponse.builder()
                        .id(pagamento.getId())
                        .tipo(pagamento.getTipo())
                        .build())
                .build();
    }
}
