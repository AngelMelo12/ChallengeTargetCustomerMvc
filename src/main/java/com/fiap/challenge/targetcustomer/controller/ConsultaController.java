package com.fiap.challenge.targetcustomer.controller;

import com.fiap.challenge.targetcustomer.model.Consulta;
import com.fiap.challenge.targetcustomer.model.dto.ConsultaDTO;
import com.fiap.challenge.targetcustomer.model.dto.ConsultaUpdateDTO;
import com.fiap.challenge.targetcustomer.service.ConsultaService;
import com.fiap.challenge.targetcustomer.utils.URIBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public String getConsultas(Model model) {
        model.addAttribute("consultas", consultaService.findAll());
        return "consultas";
    }

    @GetMapping("/new")
    public String getForm(Model model) {

        return "new-consulta";
    }

    @GetMapping("{id}")
    public ResponseEntity<Consulta> get(@PathVariable Long id) {
        return consultaService
                .get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        log.info("Apagando consulta por id: {}", id);
        consultaService.destroy(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("{id}")
    public ResponseEntity<Consulta> update(@PathVariable Long id, @RequestBody ConsultaUpdateDTO consultaRequest){
        log.info("Atualizando e-mail de empresa id {} para {}", id, consultaRequest);
        return ResponseEntity.ok(consultaService.update(id, consultaRequest));
    }
}
