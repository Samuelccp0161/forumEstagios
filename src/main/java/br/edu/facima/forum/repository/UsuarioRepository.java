package br.edu.facima.forum.repository;

import br.edu.facima.forum.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
//    @Query(value = "SELECT * FROM user WHERE email=?",nativeQuery = true)
    Optional<Usuario> findByEmail(String email);
}