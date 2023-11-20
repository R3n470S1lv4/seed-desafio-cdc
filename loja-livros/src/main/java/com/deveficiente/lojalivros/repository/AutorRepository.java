package com.deveficiente.lojalivros.repository;

import com.deveficiente.lojalivros.domain.Autor;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

  Optional<Autor> findByEmailValue(String email);
}
