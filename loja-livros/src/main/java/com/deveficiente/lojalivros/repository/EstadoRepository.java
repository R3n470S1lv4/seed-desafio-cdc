package com.deveficiente.lojalivros.repository;

import com.deveficiente.lojalivros.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, String> {

}
