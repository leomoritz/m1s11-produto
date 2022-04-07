package com.senai.exerciciosm1s11.service;

import com.senai.exerciciosm1s11.model.Produto;
import com.senai.exerciciosm1s11.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }

    public Produto findById(Long id){
        Optional<Produto> produto = produtoRepository.findById(id);

        if(produto.isPresent()){
            return produto.get();
        }

        throw new RuntimeException("Produto com id " + id + " não existe");

    }

    public Produto save(Produto produto){
        if(produto == null){
            throw new RuntimeException("Falha ao cadastrar o produto. Produto não encontrado ou não existe.");
        }

        return produtoRepository.save(produto);
    }

    public Produto update(Long id, Produto novoProduto){

        Produto produto = findById(id);

        if(produto == null || novoProduto == null){
            throw new RuntimeException("Falha ao atualizar o produto. Produto não encontrado ou não existe.");
        }

        produto.setNome(novoProduto.getNome());
        produto.setDescricao(novoProduto.getDescricao());
        produto.setDataLancamento(novoProduto.getDataLancamento());
        produto.setValor(novoProduto.getValor());

        return produtoRepository.save(novoProduto);
    }

    public void delete(Long id){
        Produto produto = findById(id);

        if(produto == null){
            throw new RuntimeException("Falha ao deletar o produto. Produto não encontrado ou não existe.");
        }

        produtoRepository.delete(produto);

    }




}
