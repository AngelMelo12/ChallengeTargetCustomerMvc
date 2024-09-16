package com.fiap.challenge.targetcustomer.controller;

import com.fiap.challenge.targetcustomer.model.EmailEmpresa;
import com.fiap.challenge.targetcustomer.model.dto.EmailEmpresaDTO;
import com.fiap.challenge.targetcustomer.model.dto.EmailEmpresaUpdateDTO;
import com.fiap.challenge.targetcustomer.service.EmailEmpresaService;
import com.fiap.challenge.targetcustomer.utils.URIBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("email")
@CacheConfig(cacheNames = "emailsEmpresas")
@Slf4j
public class EmailEmpresaController {

    @Autowired
    private EmailEmpresaService emailEmpresaService;

    @GetMapping
    public ResponseEntity<Page<EmailEmpresa>> index(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(emailEmpresaService.index(pageable));
    }

    @PostMapping
    public ResponseEntity<EmailEmpresa> create(@RequestBody @Valid EmailEmpresaDTO emailEmpresaRequest) {
        log.info("Cadastrando empresa: {}", emailEmpresaRequest);
        var emailEmpresa = emailEmpresaService.create(emailEmpresaRequest);
        return ResponseEntity
                .created(URIBuilder.createFromId(emailEmpresa.getId()))
                .build();
    }

    @GetMapping("{id}")
    public ResponseEntity<EmailEmpresa> get(@PathVariable Long id) {
        log.info("Buscar por id: {}", id);

        return emailEmpresaService
                .get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        log.info("Apagando e-mail id: {}", id);
        emailEmpresaService.destroy(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("{id}")
    public ResponseEntity<EmailEmpresa> update(@PathVariable Long id, @RequestBody EmailEmpresaUpdateDTO emailEmpresaRequest){
        log.info("Atualizando e-mail de empresa id {} para {}", id, emailEmpresaRequest);
        return ResponseEntity.ok(emailEmpresaService.update(id, emailEmpresaRequest));
    }
}
