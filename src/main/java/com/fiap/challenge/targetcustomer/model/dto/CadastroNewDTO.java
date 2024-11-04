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
public class CadastroNewDTO {

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

    public static Cadastro toCadastro(CadastroNewDTO dto) {
        var cadastro = new Cadastro();

        cadastro.setCnpj(dto.getCnpj());
        cadastro.setRazaoSocial(dto.getRazaoSocial());

        var enderecoEmpresa = new EnderecoEmpresa();
        enderecoEmpresa.setLogradouro(dto.getLogradouro());
        enderecoEmpresa.setCep(dto.getCep());
        enderecoEmpresa.setDescricaoPontoDeReferencia(dto.getDescricaoPontoDeReferencia());
        enderecoEmpresa.setCadastro(cadastro);
        var enderecosEmpresas = List.of(enderecoEmpresa);

        var emailEmpresa = new EmailEmpresa();
        emailEmpresa.setEmail(dto.getEmail());
        emailEmpresa.setCadastro(cadastro);
        var emailEmpresas = List.of(emailEmpresa);

        var telefoneEmpresa = new TelefoneEmpresa();
        telefoneEmpresa.setDdi(dto.getDdi());
        telefoneEmpresa.setDdd(dto.getDdd());
        telefoneEmpresa.setTelefone(dto.getTelefone());
        telefoneEmpresa.setTipoTelefone(dto.getTipoTelefone());
        telefoneEmpresa.setCadastro(cadastro);
        var telefonesEmpresas = List.of(telefoneEmpresa);

        cadastro.setEnderecosEmpresas(enderecosEmpresas);
        cadastro.setEmailEmpresas(emailEmpresas);
        cadastro.setTelefonesEmpresas(telefonesEmpresas);

        return cadastro;
    }
}
