package br.edu.facima.forum.repository;

import br.edu.facima.forum.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    Optional<String> findByComentario(String comentario);
}
