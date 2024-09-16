package com.fiap.challenge.targetcustomer.repository;

import com.fiap.challenge.targetcustomer.model.TelefoneEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneEmpresaRepository extends JpaRepository<TelefoneEmpresa, Long> {
}
