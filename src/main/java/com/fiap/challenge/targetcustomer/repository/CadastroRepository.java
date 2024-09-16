package com.fiap.challenge.targetcustomer.repository;

import com.fiap.challenge.targetcustomer.model.Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastroRepository extends JpaRepository<Cadastro, Long> {
}
