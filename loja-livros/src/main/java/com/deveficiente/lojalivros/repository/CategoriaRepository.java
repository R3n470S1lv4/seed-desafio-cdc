package com.deveficiente.lojalivros.repository;

import com.deveficiente.lojalivros.domain.Categoria;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

  Optional<Categoria> findByNome(String nome);
}
