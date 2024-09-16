package com.fiap.challenge.targetcustomer.repository;

import com.fiap.challenge.targetcustomer.model.EmailEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailEmpresaRepository extends JpaRepository<EmailEmpresa, Long> {
}
