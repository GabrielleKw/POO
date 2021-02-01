package com.example.demo.config.converter;

import com.example.demo.domain.model.Fornecedor;
import com.example.demo.domain.model.Funcionario;
import com.example.demo.dto.*;

public class FuncionarioResponseConverter {
    public FuncionarioResponse fromFuncionario(Funcionario funcionario){
        var endereco = funcionario.getEndereco();
        var cidade = endereco.getCidade();
        var estado = cidade.getEstado();
        return FuncionarioResponse.builder()
                .id(funcionario.getId())
                .cpf(funcionario.getCpf())
                .nome(funcionario.getNome())
                .telefone(funcionario.getTelefone())
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
