package com.fiap.challenge.targetcustomer.service;

import com.fiap.challenge.targetcustomer.model.AnaliseConsulta;
import com.fiap.challenge.targetcustomer.model.AnaliseConsultaStatus;
import com.fiap.challenge.targetcustomer.model.Cadastro;
import com.fiap.challenge.targetcustomer.model.Consulta;
import com.fiap.challenge.targetcustomer.model.dto.CadastroUpdateDTO;
import com.fiap.challenge.targetcustomer.model.dto.ConsultaNewDTO;
import com.fiap.challenge.targetcustomer.model.dto.ConsultaUpdateDTO;
import com.fiap.challenge.targetcustomer.repository.AnaliseConsultaRepository;
import com.fiap.challenge.targetcustomer.repository.CadastroRepository;
import com.fiap.challenge.targetcustomer.repository.ConsultaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final AnaliseConsultaRepository analiseConsultaRepository;
    private final CadastroRepository cadastroRepository;

    public List<Consulta> findAll() {
        return consultaRepository.findAll();
    }

    public void create(ConsultaNewDTO consultaNewDTO) {
        var consulta = ConsultaNewDTO.toConsulta(consultaNewDTO);
        var cadastro = cadastroRepository.findById(consultaNewDTO.getIdCadastro())
                .orElseThrow(
                        () -> new EntityNotFoundException("Cadastro não encontrado")
                );
        consulta.setCadastro(cadastro);

        consultaRepository.save(consulta);

        var analiseConsulta = new AnaliseConsulta();
        analiseConsulta.setStatus(AnaliseConsultaStatus.INDISPONIVEL.value);
        analiseConsulta.setConsulta(consulta);
        analiseConsultaRepository.save(analiseConsulta);
    }

    public ConsultaUpdateDTO getDtoFromId(Long id) {
        return ConsultaUpdateDTO.fromConsulta(verificarSeExisteConsulta(id));
    }

    public void delete(Long id) {
        verificarSeExisteConsulta(id);
        consultaRepository.deleteById(id);
    }

    public Consulta update(ConsultaUpdateDTO consultaUpdateDTO){
        var consultaToUpdate = verificarSeExisteConsulta(consultaUpdateDTO.getConsultaId());
        consultaToUpdate.setDescricaoConsulta(consultaUpdateDTO.getDescricaoConsulta());
        return consultaRepository.save(consultaToUpdate);
    }

    private Consulta verificarSeExisteConsulta(Long id) {
        return consultaRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Consulta não encontrado")
                );
    }
}
