package com.deveficiente.lojalivros.repository;

import com.deveficiente.lojalivros.domain.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, String> {

}
