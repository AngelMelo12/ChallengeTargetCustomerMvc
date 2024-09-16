package com.fiap.challenge.targetcustomer.model.dto;

import com.fiap.challenge.targetcustomer.model.Cadastro;
import com.fiap.challenge.targetcustomer.model.EmailEmpresa;
import com.fiap.challenge.targetcustomer.model.EnderecoEmpresa;
import com.fiap.challenge.targetcustomer.model.TelefoneEmpresa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CadastroUpdateDTO {

    @NotNull
    private Long id;

    @NotNull
    private Long cnpj;

    @NotBlank
    @Size(min = 5, max = 60, message = "Senha deve ter no mínimo 5 caracteres e no máximo 60 caracteres")
    private String senha;

    @NotBlank
    @Size(min = 5, max = 80)
    private String razaoSocial;

    // Endereço
    @NotNull
    private Long logradouro;

    @NotNull
    private Long cep;

    @NotBlank
    private String descricaoPontoDeReferencia;

    // E-mail
    @NotNull @Email(message = "E-mail deve estar em formatação válida")
    private String email;

    // Telefone
    @NotNull
    private Long ddi;

    @NotNull
    private Long ddd;

    @NotNull
    private Long telefone;

    @NotNull
    @Size(max = 20)
    private String tipoTelefone;

    public static Cadastro toCadastro(Cadastro cadastro, CadastroUpdateDTO dto) {
        cadastro.setCnpj(dto.getCnpj());
        cadastro.setSenha(dto.getSenha());
        cadastro.setRazaoSocial(dto.getRazaoSocial());

        var enderecosEmpresas = cadastro.getEnderecosEmpresas();
        var enderecoEmpresaCadastrado = enderecosEmpresas
                .stream()
                .findFirst()
                .orElse(new EnderecoEmpresa());

        enderecoEmpresaCadastrado.setLogradouro(dto.getLogradouro());
        enderecoEmpresaCadastrado.setCep(dto.getCep());
        enderecoEmpresaCadastrado.setDescricaoPontoDeReferencia(dto.getDescricaoPontoDeReferencia());

        enderecosEmpresas.add(enderecoEmpresaCadastrado);

        var emailEmpresas = cadastro.getEmailEmpresas();
        var emailCadastrado = emailEmpresas
                .stream()
                .findFirst()
                .orElse(new EmailEmpresa());

        emailCadastrado.setEmail(dto.getEmail());

        emailEmpresas.add(emailCadastrado);

        var telefonesEmpresas = cadastro.getTelefonesEmpresas();
        var telefoneCadastrado = telefonesEmpresas
                .stream()
                .findFirst()
                .orElse(new TelefoneEmpresa());

        telefoneCadastrado.setDdi(dto.getDdi());
        telefoneCadastrado.setDdd(dto.getDdd());
        telefoneCadastrado.setTelefone(dto.getTelefone());
        telefoneCadastrado.setTipoTelefone(dto.getTipoTelefone());

        telefonesEmpresas.add(telefoneCadastrado);

        cadastro.setEnderecosEmpresas(enderecosEmpresas);
        cadastro.setEmailEmpresas(emailEmpresas);
        cadastro.setTelefonesEmpresas(telefonesEmpresas);

        return cadastro;
    }

    public static CadastroUpdateDTO fromCadastro(Cadastro cadastro) {
        var cadastroUpdateDTO = new CadastroUpdateDTO();
        var enderecoEmpresa = cadastro.getEnderecosEmpresas()
                .stream().findFirst().orElseThrow();
        var emailEmpresa = cadastro.getEmailEmpresas()
                .stream().findFirst().orElseThrow();
        var telefoneEmpresa = cadastro.getTelefonesEmpresas()
                .stream().findFirst().orElseThrow();

        cadastroUpdateDTO.setId(cadastro.getId());
        cadastroUpdateDTO.setCnpj(cadastro.getCnpj());
        cadastroUpdateDTO.setSenha(cadastro.getSenha());
        cadastroUpdateDTO.setRazaoSocial(cadastro.getRazaoSocial());
        cadastroUpdateDTO.setLogradouro(enderecoEmpresa.getLogradouro());
        cadastroUpdateDTO.setCep(enderecoEmpresa.getCep());
        cadastroUpdateDTO.setDescricaoPontoDeReferencia(enderecoEmpresa.getDescricaoPontoDeReferencia());
        cadastroUpdateDTO.setEmail(emailEmpresa.getEmail());
        cadastroUpdateDTO.setDdi(telefoneEmpresa.getDdi());
        cadastroUpdateDTO.setDdd(telefoneEmpresa.getDdd());
        cadastroUpdateDTO.setTelefone(telefoneEmpresa.getTelefone());
        cadastroUpdateDTO.setTipoTelefone(telefoneEmpresa.getTipoTelefone());

        return cadastroUpdateDTO;
    }
}
