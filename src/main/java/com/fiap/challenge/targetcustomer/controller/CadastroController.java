package com.fiap.challenge.targetcustomer.controller;

import com.fiap.challenge.targetcustomer.model.Cadastro;
import com.fiap.challenge.targetcustomer.model.dto.CadastroDTO;
import com.fiap.challenge.targetcustomer.model.dto.CadastroUpdateDTO;
import com.fiap.challenge.targetcustomer.service.CadastroService;
import com.fiap.challenge.targetcustomer.utils.URIBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("cadastro")
@Slf4j
public class CadastroController {

    @Autowired
    private CadastroService cadastroService;

    @GetMapping
    public ResponseEntity<Page<Cadastro>> index(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(cadastroService.index(pageable));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Cadastro> create(@RequestBody @Valid CadastroDTO cadastroRequest) {
        log.info("Cadastrando empresa: {}", cadastroRequest);
        var createdCadastro = cadastroService.create(cadastroRequest);
        return ResponseEntity
                .created(URIBuilder.createFromId(createdCadastro.getId()))
                .build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Cadastro> get(@PathVariable Long id) {
        log.info("Buscar por id: {}", id);
        return cadastroService.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound()
                .build());
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        log.info("Apagando cadastro por id: {}", id);
        cadastroService.destroy(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<Cadastro> update(@PathVariable Long id, @RequestBody CadastroUpdateDTO cadastroRequest){
        log.info("Atualizando cadastro id {} para {}", id, cadastroRequest);
        return ResponseEntity.ok(cadastroService.update(id, cadastroRequest));
    }
}
