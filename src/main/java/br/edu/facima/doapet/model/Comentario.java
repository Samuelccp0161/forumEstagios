package br.edu.facima.doapet.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "COMENTARIO")
@JsonPropertyOrder({"comentario", "autorEmail", "animalId"})
public class Comentario implements Serializable {
    @Serial private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 500, nullable = false)
    private String comentario;
    @Column(length = 150, nullable = false)
    private String autorEmail;
    @Column(nullable = false)
    private Long animalId;

    public Comentario() {}

    public Comentario(String autorEmail,Long animalId,String comentario) {
        this.comentario = comentario;
        this.autorEmail = autorEmail;
        this.animalId = animalId;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getAutorEmail() {
        return autorEmail;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAutorEmail(String autorEmail) {
        this.autorEmail = autorEmail;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comentario that = (Comentario) o;

        if (!comentario.equals(that.comentario)) return false;
        if (!autorEmail.equals(that.autorEmail)) return false;
        return animalId.equals(that.animalId);
    }

    @Override
    public int hashCode() {
        int result = comentario.hashCode();
        result = 31 * result + autorEmail.hashCode();
        result = 31 * result + animalId.hashCode();
        return result;
    }
}
