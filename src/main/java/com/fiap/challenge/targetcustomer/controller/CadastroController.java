package com.fiap.challenge.targetcustomer.controller;

import com.fiap.challenge.targetcustomer.model.dto.CadastroNewDTO;
import com.fiap.challenge.targetcustomer.model.dto.CadastroUpdateDTO;
import com.fiap.challenge.targetcustomer.service.CadastroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cadastros")
public class CadastroController {

    @Autowired
    private CadastroService cadastroService;

    @GetMapping
    public String getCadastros(Model model) {
        model.addAttribute("cadastros", cadastroService.findAll());
        return "cadastros";
    }

    @GetMapping("/create")
    public String getForm(Model model) {
        model.addAttribute("formDto", new CadastroNewDTO());
        return "new-cadastro";
    }

    @PostMapping
    public String newCadastro(@ModelAttribute @Valid CadastroNewDTO cadastroNewDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new-cadastro";
        }
        cadastroService.create(cadastroNewDTO);
        return "redirect:/cadastros";
    }


    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        cadastroService.delete(id);
        return "redirect:/cadastros";
    }

    @GetMapping("/edit/{id}")
    public String getFormEdicao(Model model, @PathVariable Long id){
        CadastroUpdateDTO cadastroUpdateDTO = cadastroService.getDtoFromId(id);
        model.addAttribute("cadastroUpdateDTO", cadastroUpdateDTO);
        return "update-cadastro";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute @Valid CadastroUpdateDTO cadastroUpdateDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "update-cadastro";
        }
        cadastroService.update(cadastroUpdateDTO);
        return "redirect:/medicos";
    }
}
