package br.edu.facima.forum.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "ANIMAL")
@JsonPropertyOrder({"nome", "telefone", "descricao"})
public class Animal implements Serializable {
    @Serial private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 150, nullable = false)
    private String nome;
    @Column(nullable = false)
    private String telefone;
    @Column(nullable = false)
    private String descricao;

    public Animal() {}

    public Animal(String nome, String telefone, String descricao) {
        this.nome = nome;
        this.telefone = telefone;
        this.descricao = descricao;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return nome;
    }

    public String getTelefone(){
        return telefone;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{\"nome\":\"Fran\",\"telefone\":\"829886645\",\"descricao\":\"Muito Birrenta\"}";
    }
}
