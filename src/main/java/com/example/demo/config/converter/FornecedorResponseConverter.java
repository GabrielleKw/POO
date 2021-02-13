package com.example.demo.config.converter;

import com.example.demo.domain.model.Fornecedor;
import com.example.demo.dto.CidadeResponse;
import com.example.demo.dto.EnderecoResponse;
import com.example.demo.dto.EstadoResponse;
import com.example.demo.dto.FornecedorResponse;
import org.springframework.stereotype.Component;

@Component
public class FornecedorResponseConverter {
    public FornecedorResponse fromFornecedor(Fornecedor fornecedor){
        var endereco = fornecedor.getEndereco();
        var cidade = endereco.getCidade();
        var estado = cidade.getEstado();
        return FornecedorResponse.builder()
                .id(fornecedor.getId())
                .cnpj(fornecedor.getCnpj())
                .nome(fornecedor.getNome())
                .telefone(fornecedor.getTelefone())
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
