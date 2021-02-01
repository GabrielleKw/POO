package com.example.demo.config.converter;

import com.example.demo.domain.model.Tributacao;
import com.example.demo.dto.TributacaoResponse;

public class TributacaoResponseConverter {
    public TributacaoResponse fromTributacao(Tributacao tributacao){
        return TributacaoResponse.builder()
                .id(tributacao.getId())
                .descricao(tributacao.getDescricao())
                .valor(tributacao.getValor())
                .familia(tributacao.getFamilia())
                .build();
    }
}
