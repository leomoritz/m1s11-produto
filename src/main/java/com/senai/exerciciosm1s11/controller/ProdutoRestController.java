package com.senai.exerciciosm1s11.controller;

import com.senai.exerciciosm1s11.model.Produto;
import com.senai.exerciciosm1s11.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
public class ProdutoRestController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> getProdutos(){
        return produtoService.findAll();
    }

    @GetMapping("/buscaProduto/{id}")
    public Produto getProdutoPorId(@PathVariable Long id){
        return produtoService.findById(id);
    }

    @PostMapping("/cadastraProduto")
    public Produto cadastraProduto(@RequestBody Produto produto){
        return produtoService.save(produto);
    }

    @PutMapping("/atualizaProduto/{id}")
    public Produto atualizaProduto(@PathVariable Long id, @RequestBody Produto novoProduto){
        return produtoService.update(id, novoProduto);
    }

    @DeleteMapping("/deletaProduto/{id}")
    public void deletaProduto(@PathVariable Long id){
        produtoService.delete(id);
    }

}
