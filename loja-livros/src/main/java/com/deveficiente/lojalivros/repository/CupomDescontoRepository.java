package com.deveficiente.lojalivros.repository;

import com.deveficiente.lojalivros.domain.CupomDesconto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CupomDescontoRepository extends JpaRepository<CupomDesconto, String> {

}
