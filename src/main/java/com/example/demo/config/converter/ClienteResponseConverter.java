package com.example.demo.config.converter;

import com.example.demo.domain.model.Cliente;
import com.example.demo.dto.CidadeResponse;
import com.example.demo.dto.ClienteResponse;
import com.example.demo.dto.EnderecoResponse;
import com.example.demo.dto.EstadoResponse;
import org.springframework.stereotype.Component;

@Component
public class ClienteResponseConverter {
    public ClienteResponse fromCliente(Cliente cliente) {
        var endereco = cliente.getEndereco();
        var cidade = endereco.getCidade();
        var estado = cidade.getEstado();
        return ClienteResponse.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .cnpj(cliente.getCnpj())
                .cpf(cliente.getCpf())
                .telefone(cliente.getTelefone())
                .endereco(EnderecoResponse.builder()
                        .id(endereco.getId())
                        .bairro(endereco.getBairro())
                        .cep(endereco.getCep())
                        .rua(endereco.getRua())
                        .complemento(endereco.getComplemento())
                        .numero(endereco.getNumero())
                        .cidade(CidadeResponse.builder()
                                .id(cidade.getId())
                                .nome(cidade.getNome())
                                .estado(EstadoResponse.builder()
                                        .id(estado.getId())
                                        .nome(estado.getNome())
                                        .sigla(estado.getSigla())
                                        .build())
                                .build())
                        .build())
                .build();
    }
}
