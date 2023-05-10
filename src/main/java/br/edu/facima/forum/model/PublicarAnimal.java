package br.edu.facima.forum.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;

@JsonPropertyOrder({"nome", "telefone", "endereco", "descricao"})
public class PublicarAnimal {

    @Column(length = 150, nullable = false)
    private String nome;

    @Column(nullable = false)
    private Long telefone;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String descricao;

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }
    public Long getTelefone(){
        return telefone;
    }

}
