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

    @NotNull(message = "CNPJ não pode ser vazio!")
    private Long cnpj;

    @NotBlank(message = "Razão social não pode ser vazia!")
    @Size(min = 1, max = 80, message = "Razão social muito curta!")
    private String razaoSocial;

    // Endereço
    @NotNull(message = "Logradouro não pode ser vazio!")
    private String logradouro;

    @NotNull(message = "CEP não pode ser vazio!")
    private Long cep;

    @NotBlank(message = "Digite alguma referência!")
    private String descricaoPontoDeReferencia;

    // E-mail
    @NotNull(message = "E-mail não pode ser vazio!")
    @Email(message = "E-mail deve estar em formatação válida")
    private String email;

    // Telefone
    @NotNull(message = "DDI não pode ser vazio!")
    private Long ddi;

    @NotNull(message = "DDD não pode ser vazio!")
    private Long ddd;

    @NotNull(message = "Telefone não pode ser vazio!")
    private Long telefone;

    @NotNull(message = "Tipo do telefone não pode ser vazio!")
    @Size(max = 20)
    private String tipoTelefone;

    public static Cadastro toCadastro(Cadastro cadastro, CadastroUpdateDTO dto) {
        cadastro.setCnpj(dto.getCnpj());
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
