package com.example.demo.service;

import com.example.demo.config.converter.FuncionarioResponseConverter;
import com.example.demo.domain.model.Endereco;
import com.example.demo.domain.model.Funcionario;
import com.example.demo.domain.model.StatusEnum;
import com.example.demo.domain.repository.FuncionarioRepository;
import com.example.demo.dto.FornecedorRequest;
import com.example.demo.dto.FuncionarioRequest;
import com.example.demo.dto.FuncionarioResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {
   private final FuncionarioRepository funcionarioRepository;
   private final FuncionarioResponseConverter funcionarioResponseConverter;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, FuncionarioResponseConverter funcionarioResponseConverter) {
        this.funcionarioRepository = funcionarioRepository;
        this.funcionarioResponseConverter = funcionarioResponseConverter;
    }

    public FuncionarioResponse salvarFornecedor(FuncionarioRequest funcionarioRequest){
        var enderecoRequest = funcionarioRequest.getEndereco();

        var funcionario = Funcionario.builder()
                .id(funcionarioRequest.getId())
                .nome(funcionarioRequest.getNome())
                .telefone(funcionarioRequest.getTelefone())
                .cpf(funcionarioRequest.getCpf())
                .dataCadastro(LocalDateTime.now())
                .endereco(Endereco.builder()
                        .id(enderecoRequest.getId())
                        .build())
                .status(StatusEnum.ATIVO)
                .build();
        funcionarioRepository.save(funcionario);
        return funcionarioResponseConverter.fromFuncionario(funcionario);
    }
    public List<FuncionarioResponse> buscarTodos(Pageable pageable){
        return funcionarioRepository.findAll(pageable).stream()
                .map(funcionario -> funcionarioResponseConverter.fromFuncionario(funcionario)).collect(Collectors.toList());
    }
    public FuncionarioResponse buscarById(Long id){
        return funcionarioRepository.findById(id).map(funcionario -> funcionarioResponseConverter.fromFuncionario(funcionario)).orElse(null);
    }
}
