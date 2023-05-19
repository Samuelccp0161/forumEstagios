package br.edu.facima.forum.repository;

import br.edu.facima.forum.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    Optional<String> findByComentario(String comentario);
}
