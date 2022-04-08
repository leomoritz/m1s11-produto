package com.senai.exerciciosm1s11.controller;

import com.senai.exerciciosm1s11.controller.dto.ProdutoDto;
import com.senai.exerciciosm1s11.model.Produto;
import com.senai.exerciciosm1s11.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("produto/index");
    }

    @GetMapping("/listar")
    public ModelAndView listaProdutos() {
        List<Produto> produtoLista = produtoService.findAll();
        ModelAndView mv = new ModelAndView("produto/produtos");
        mv.addObject("produtos", produtoLista);

        return mv;
    }

    @GetMapping("/cadastro")
    public ModelAndView novoProduto(ProdutoDto novoProdutoDto) {
        return new ModelAndView("produto/cadastro");
    }

    @PostMapping("/cadastrar")
    public ModelAndView cadastrarProduto(@Valid ProdutoDto novoProdutoDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("produto/cadastro");
        } else {
            Produto novoProduto = novoProdutoDto.converteParaProduto();
            produtoService.save(novoProduto);
            return new ModelAndView("redirect:/produto/listar");
        }
    }

    @GetMapping("/{id}/atualizar")
    public ModelAndView atualizacaoProduto(@PathVariable Long id, ProdutoDto novoProdutoDto) {
        Produto produto = produtoService.findById(id);
        novoProdutoDto.converterParaDto(produto);
        ModelAndView mv = new ModelAndView("produto/atualizacao");
        mv.addObject("produtoId", produto.getId());
        return mv;
    }

    @PostMapping("/{id}")
    public ModelAndView atualizar(@PathVariable Long id, @Valid ProdutoDto novoProdutoDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("produto/atualizacao");
        } else {
            Produto novoProduto = novoProdutoDto.converteParaProduto();
            produtoService.update(id, novoProduto);
            return new ModelAndView("redirect:/produto/listar");
        }
    }

    @GetMapping("/{id}/deletar")
    public ModelAndView deletar(@PathVariable Long id) {
        produtoService.delete(id);
        return new ModelAndView("redirect:/produto/listar");
    }

}
