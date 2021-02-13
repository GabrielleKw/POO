package com.example.demo.service;

import com.example.demo.config.converter.ListaProdutosResponseConverter;
import com.example.demo.domain.model.ListaProdutos;
import com.example.demo.domain.repository.*;
import com.example.demo.dto.ListaProdutosRequest;
import com.example.demo.dto.ListaProdutosResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VendaService {
    private final ListaProdutosRepository listaProdutosRepository;
    private final ProdutoRepository produtoRepository;
    private final VendaFinalRepository vendaFinalRepository;
    private final PrecoCustoRepository precoCustoRepository;
    private final PrecoVendaRepository precoVendaRepository;
    private final ListaProdutosResponseConverter listaProdutosResponseConverter;

    public VendaService(ListaProdutosRepository listaProdutosRepository, ProdutoRepository produtoRepository, VendaFinalRepository vendaFinalRepository, PrecoCustoRepository precoCustoRepository, PrecoVendaRepository precoVendaRepository, ListaProdutosResponseConverter listaProdutosResponseConverter) {
        this.listaProdutosRepository = listaProdutosRepository;
        this.produtoRepository = produtoRepository;
        this.vendaFinalRepository = vendaFinalRepository;
        this.precoCustoRepository = precoCustoRepository;
        this.precoVendaRepository = precoVendaRepository;
        this.listaProdutosResponseConverter = listaProdutosResponseConverter;
    }

    public ListaProdutosResponse novaVenda(Long idProduto, Long idVendaFinal, Long idPrecoCusto, Long idPrecoVenda){
        var produto = produtoRepository.findById(idProduto).orElseThrow(() -> new RuntimeException("Produto não econtrado"));
        var vendaFinal = vendaFinalRepository.findById(idVendaFinal).orElseThrow(() -> new RuntimeException("Venda não encontrada"));
        var precoCusto = precoCustoRepository.findById(idPrecoCusto).orElseThrow(() -> new RuntimeException("Preço de custo não cadastrado"));
        var precoVenda = precoVendaRepository.findById(idPrecoVenda).orElseThrow(() -> new RuntimeException("Preço de venda não cadastrado"));
        var venda = ListaProdutos.builder()
                .produto(produto)
                .vendaFinal(vendaFinal)
                .precoCusto(precoCusto)
                .precoVenda(precoVenda)
                .dataCadastro(LocalDateTime.now())
                .build();
        listaProdutosRepository.save(venda);
        return listaProdutosResponseConverter.fromListaProdutos(venda);
    }
    public ListaProdutosResponse concluirVenda(Long vendaId){
        var venda = listaProdutosRepository.findById(vendaId).orElseThrow(() -> new RuntimeException("Venda não encontrada"));
        listaProdutosRepository.save(venda);
        return listaProdutosResponseConverter.fromListaProdutos(venda);
    }

}
