package com.deveficiente.lojalivros.repository;

import com.deveficiente.lojalivros.domain.Pais;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository<Pais, String> {

  Optional<Pais> findByNomeIgnoreCase(String nome);
}
