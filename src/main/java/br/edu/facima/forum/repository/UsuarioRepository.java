package br.edu.facima.forum.repository;

import br.edu.facima.forum.model.Usuario;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;

import java.util.Optional;
import java.util.function.Function;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
//    @Query(value = "SELECT * FROM user WHERE email=",nativeQuery = true)
    Optional<Usuario> findByEmail(String email);
}
