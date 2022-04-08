package com.senai.exerciciosm1s11.controller.dto;

import com.senai.exerciciosm1s11.model.Produto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ProdutoDto {

    @NotBlank
    private String nome;

    private String descricao;

    @NotBlank
    private String dataLancamento;

    @NotNull
    @DecimalMin("0.0")
    private Double valor;

    public ProdutoDto(String nome, String descricao, String dataLancamento, Double valor) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataLancamento = dataLancamento;
        this.valor = valor;
    }

    public Produto converteParaProduto(){
        Produto novoProduto = new Produto();
        novoProduto.setNome(this.nome);
        novoProduto.setDescricao(this.descricao);
        novoProduto.setDataLancamento(LocalDate.parse(this.dataLancamento, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        novoProduto.setValor(this.valor);
        return novoProduto;
    }


    @Override
    public String toString() {
        return "ProdutoDto{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataLancamento='" + dataLancamento + '\'' +
                ", valor=" + valor +
                '}';
    }
}
