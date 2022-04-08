package com.senai.exerciciosm1s11.controller;

import com.senai.exerciciosm1s11.controller.dto.ProdutoDto;
import com.senai.exerciciosm1s11.model.Produto;
import com.senai.exerciciosm1s11.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("produto/index");
    }

    @GetMapping("/produto/listar")
    public ModelAndView listaProdutos() {
        List<Produto> produtoLista = produtoService.findAll();
        ModelAndView mv = new ModelAndView("produto/produtos");
        mv.addObject("produtos", produtoLista);

        return mv;
    }

    @GetMapping("/produto/cadastro")
    public ModelAndView novoProduto(ProdutoDto novoProdutoDto) {
        return new ModelAndView("produto/cadastro");
    }

    @PostMapping("/produto/cadastrar")
    public ModelAndView cadastrarProduto(@Valid ProdutoDto novoProdutoDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("produto/cadastro");
        } else {
            Produto novoProduto = novoProdutoDto.converteParaProduto();
            produtoService.save(novoProduto);
            return new ModelAndView("redirect:/produto/listar");
        }
    }

}
