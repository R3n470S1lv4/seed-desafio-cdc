package com.deveficiente.lojalivros.repository;

import com.deveficiente.lojalivros.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, String> {

}
