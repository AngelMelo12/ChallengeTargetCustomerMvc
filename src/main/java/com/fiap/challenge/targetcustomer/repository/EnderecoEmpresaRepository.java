package com.fiap.challenge.targetcustomer.repository;

import com.fiap.challenge.targetcustomer.model.EnderecoEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoEmpresaRepository extends JpaRepository<EnderecoEmpresa, Long> {
}
