package com.senai.exerciciosm1s11.controller;

import com.senai.exerciciosm1s11.model.Produto;
import com.senai.exerciciosm1s11.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("produto/index");
    }

    @GetMapping("/produtos/cadastrar")
    public ModelAndView cadastrarProduto(Produto novoProduto){
        Produto produto = produtoService.save(novoProduto);
        ModelAndView mv = new ModelAndView("produto/cadastra-produto");
        mv.addObject("produtos", produto);

        return mv;
    }

    @GetMapping("/produtos")
    public ModelAndView listaProdutos(){
        List<Produto> produtoLista = produtoService.findAll();
        ModelAndView mv = new ModelAndView("produto/produtos");
        mv.addObject("produtos", produtoLista);

        return mv;
    }

}
