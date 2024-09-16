package com.fiap.challenge.targetcustomer.service;

import com.fiap.challenge.targetcustomer.model.Cadastro;
import com.fiap.challenge.targetcustomer.model.dto.CadastroNewDTO;
import com.fiap.challenge.targetcustomer.model.dto.CadastroUpdateDTO;
import com.fiap.challenge.targetcustomer.repository.CadastroRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CadastroService {

    private final CadastroRepository cadastroRepository;

    public List<Cadastro> findAll() {
        return cadastroRepository.findAll();
    }

    public void create(CadastroNewDTO cadastroRequest) {
        cadastroRepository.save(CadastroNewDTO.toCadastro(cadastroRequest));
    }

    public CadastroUpdateDTO getDtoFromId(Long id) {
        return cadastroRepository.findById(id)
                .map(CadastroUpdateDTO::fromCadastro)
                .orElseThrow();
    }

    public void delete(Long id) {
        verificarSeExisteCadastro(id);
        cadastroRepository.deleteById(id);
    }

    public Cadastro update(CadastroUpdateDTO cadastroRequest){
        var cadastroToUpdate = verificarSeExisteCadastro(cadastroRequest.getId());
        return cadastroRepository.save(CadastroUpdateDTO.toCadastro(cadastroToUpdate, cadastroRequest));
    }

    private Cadastro verificarSeExisteCadastro(Long id) {
        return cadastroRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Cadastro não encontrado" )
                );
    }
}
