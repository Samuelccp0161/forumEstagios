package br.edu.facima.forum.model;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@JsonPropertyOrder({"nome", "email", "contato", "senha"})
public class Usuario implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private Long contato;

    @Column(length = 25, nullable = false)
    private String senha;

    @Column()
    private final Comentario comentario = new Comentario();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }
    public String getSenha() {
        return senha;
    }

    public Long getContato() {
        return contato;
    }

    public void setContato(Long contato) {
        this.contato = contato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return nome.equals(usuario.nome) && email.equals(usuario.email) && contato.equals(usuario.contato) && senha.equals(usuario.senha);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", contato=" + contato +
                ", senha='" + senha + '\'' +
                '}';
    }
}
