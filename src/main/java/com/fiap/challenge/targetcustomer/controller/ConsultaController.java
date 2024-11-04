package com.fiap.challenge.targetcustomer.controller;

import com.fiap.challenge.targetcustomer.model.dto.CadastroNewDTO;
import com.fiap.challenge.targetcustomer.model.dto.CadastroUpdateDTO;
import com.fiap.challenge.targetcustomer.model.dto.ConsultaNewDTO;
import com.fiap.challenge.targetcustomer.model.dto.ConsultaUpdateDTO;
import com.fiap.challenge.targetcustomer.service.CadastroService;
import com.fiap.challenge.targetcustomer.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private CadastroService cadastroService;

    @GetMapping
    public String getConsultas(Model model) {
        model.addAttribute("consultas", consultaService.findAll());
        return "consultas";
    }

    @GetMapping("/create")
    public String getForm(Model model) {
        model.addAttribute("consultaNewDTO", new ConsultaNewDTO());
        model.addAttribute("cadastros", cadastroService.findAll());
        return "new-consulta";
    }

    @PostMapping
    public String newConsulta(@ModelAttribute @Valid ConsultaNewDTO consultaNewDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new-consulta";
        }
        consultaService.create(consultaNewDTO);
        return "redirect:/consultas";
    }


    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        consultaService.delete(id);
        return "redirect:/consultas";
    }

    @GetMapping("/edit/{id}")
    public String getFormEdicao(Model model, @PathVariable Long id){
        var consultaUpdateDTO = consultaService.getDtoFromId(id);
        model.addAttribute("consultaUpdateDTO", consultaUpdateDTO);
        return "update-consulta";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute @Valid ConsultaUpdateDTO consultaUpdateDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "update-consulta";
        }
        consultaService.update(consultaUpdateDTO);
        return "redirect:/consultas";
    }
}
