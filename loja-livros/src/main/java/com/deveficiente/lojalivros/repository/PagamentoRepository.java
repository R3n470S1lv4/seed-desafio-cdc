package com.deveficiente.lojalivros.repository;

import com.deveficiente.lojalivros.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, String> {

}
