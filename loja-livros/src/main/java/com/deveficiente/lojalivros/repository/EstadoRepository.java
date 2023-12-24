package com.deveficiente.lojalivros.repository;

import com.deveficiente.lojalivros.domain.Estado;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, String> {

  boolean existsByPaisNome(String nome);

  Optional<Estado> findByNome(String nome);
}
