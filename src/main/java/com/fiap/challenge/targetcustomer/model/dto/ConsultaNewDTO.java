package com.fiap.challenge.targetcustomer.model.dto;

import com.fiap.challenge.targetcustomer.model.Consulta;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConsultaNewDTO {

    @NotNull
    private Long idCadastro;

    @NotNull
    private String descricaoConsulta;

    public static Consulta toConsulta(ConsultaNewDTO consultaDTO) {
        var consulta = new Consulta();

        consulta.setDescricaoConsulta(consultaDTO.getDescricaoConsulta());
        consulta.setDataConsulta(LocalDateTime.now());

        return consulta;
    }
}
