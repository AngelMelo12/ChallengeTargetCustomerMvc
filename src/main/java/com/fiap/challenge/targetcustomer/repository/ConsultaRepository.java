package com.fiap.challenge.targetcustomer.repository;

import com.fiap.challenge.targetcustomer.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
