package com.example.demo.service;

import com.example.demo.config.converter.FornecedorResponseConverter;
import com.example.demo.domain.model.Cliente;
import com.example.demo.domain.model.Endereco;
import com.example.demo.domain.model.Fornecedor;
import com.example.demo.domain.model.StatusEnum;
import com.example.demo.domain.repository.FornecedorRepository;
import com.example.demo.dto.ClienteRequest;
import com.example.demo.dto.ClienteResponse;
import com.example.demo.dto.FornecedorRequest;
import com.example.demo.dto.FornecedorResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FornecedorService {
   private final FornecedorRepository fornecedorRepository;
   private final FornecedorResponseConverter fornecedorResponseConverter;

    public FornecedorService(FornecedorRepository fornecedorRepository, FornecedorResponseConverter fornecedorResponseConverter) {
        this.fornecedorRepository = fornecedorRepository;
        this.fornecedorResponseConverter = fornecedorResponseConverter;
    }

    public FornecedorResponse salvarFornecedor(FornecedorRequest fornecedorRequest){
        var enderecoRequest = fornecedorRequest.getEndereco();

        var fornecedor = Fornecedor.builder()
                .id(fornecedorRequest.getId())
                .nome(fornecedorRequest.getNome())
                .telefone(fornecedorRequest.getTelefone())
                .cnpj(fornecedorRequest.getCnpj())
                .cpf(fornecedorRequest.getCpf())
                .dataCadastro(LocalDateTime.now())
                .endereco(Endereco.builder()
                        .id(enderecoRequest.getId())
                        .build())
                .status(StatusEnum.ATIVO)
                .build();
        fornecedorRepository.save(fornecedor);
        return fornecedorResponseConverter.fromFornecedor(fornecedor);
    }
    public List<FornecedorResponse> buscarTodos(Pageable pageable){
        return fornecedorRepository.findAll(pageable).stream()
                .map(fornecedor -> fornecedorResponseConverter.fromFornecedor(fornecedor)).collect(Collectors.toList());
    }
    public FornecedorResponse buscarById(Long id){
        return fornecedorRepository.findById(id).map(fornecedor -> fornecedorResponseConverter.fromFornecedor(fornecedor)).orElse(null);
    }
}
