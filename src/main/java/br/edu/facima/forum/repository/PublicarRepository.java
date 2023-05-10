package br.edu.facima.forum.repository;

import br.edu.facima.forum.model.PublicarAnimal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicarRepository extends JpaRepository<PublicarAnimal, Long> {

}
