package com.example.demo.service;

import com.example.demo.config.converter.ClienteResponseConverter;
import com.example.demo.domain.model.Cliente;
import com.example.demo.domain.model.Endereco;
import com.example.demo.domain.model.StatusEnum;
import com.example.demo.domain.repository.ClienteRepository;
import com.example.demo.dto.ClienteRequest;
import com.example.demo.dto.ClienteResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteResponseConverter clienteResponseConverter;

    public ClienteService(ClienteRepository clienteRepository, ClienteResponseConverter clienteResponseConverter) {
        this.clienteRepository = clienteRepository;
        this.clienteResponseConverter = clienteResponseConverter;
    }
    public ClienteResponse salvarCliente(ClienteRequest clienteRequest){
        var enderecoRequest = clienteRequest.getEndereco();

        var cliente = Cliente.builder()
                .id(clienteRequest.getId())
                .nome(clienteRequest.getNome())
                .telefone(clienteRequest.getTelefone())
                .cnpj(clienteRequest.getCnpj())
                .cpf(clienteRequest.getCpf())
                .dataCadastro(LocalDateTime.now())
                .endereco(Endereco.builder()
                        .id(enderecoRequest.getId())
                        .build())
                .status(StatusEnum.ATIVO)
                .build();
        clienteRepository.save(cliente);
        return clienteResponseConverter.fromCliente(cliente);
    }
    public List<ClienteResponse> buscarTodos(Pageable pageable){
        return clienteRepository.findAll(pageable).stream()
                .map(cliente -> clienteResponseConverter.fromCliente(cliente)).collect(Collectors.toList());
    }
    public ClienteResponse buscarById(Long id){
        return clienteRepository.findById(id).map(cliente -> clienteResponseConverter.fromCliente(cliente)).orElse(null);
    }
}
