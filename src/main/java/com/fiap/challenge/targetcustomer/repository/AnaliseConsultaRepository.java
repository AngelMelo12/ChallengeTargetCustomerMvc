package com.fiap.challenge.targetcustomer.repository;

import com.fiap.challenge.targetcustomer.model.AnaliseConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnaliseConsultaRepository extends JpaRepository<AnaliseConsulta, Long> {
}
