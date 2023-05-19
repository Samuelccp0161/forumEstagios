package br.edu.facima.forum.model;

import jakarta.persistence.Column;

public class Comentario {

    public Comentario() {
    }

    public Comentario(String comentario) {
        this.comentario = comentario;
    }

    @Column
    private String comentario;

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
