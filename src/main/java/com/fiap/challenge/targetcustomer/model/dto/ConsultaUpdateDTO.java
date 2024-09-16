package com.fiap.challenge.targetcustomer.model.dto;

import com.fiap.challenge.targetcustomer.model.Consulta;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConsultaUpdateDTO {

    @NotNull
    private Long consultaId;

    @NotNull
    private String descricaoConsulta;

    public static ConsultaUpdateDTO fromConsulta(Consulta consulta) {
        var consultaDTO = new ConsultaUpdateDTO();
        consultaDTO.setDescricaoConsulta(consulta.getDescricaoConsulta());
        return consultaDTO;
    }
}
