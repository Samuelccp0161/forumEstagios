package br.edu.facima.doapet.repository;

import br.edu.facima.doapet.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findByAutorEmail(String email);

    List<Comentario> findByAnimalId(Long id);
}
