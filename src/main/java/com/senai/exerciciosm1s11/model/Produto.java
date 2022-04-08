package com.senai.exerciciosm1s11.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    @Column(name = "data_lancamento", nullable = false)
    private LocalDate dataLancamento;

    @Column(nullable = false)
    private Double valor;

    public Produto() {
    }

    public Produto(Long id, String nome, String descricao, LocalDate dataLancamento, Double valor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataLancamento = dataLancamento;
        this.valor = valor;
    }
}
