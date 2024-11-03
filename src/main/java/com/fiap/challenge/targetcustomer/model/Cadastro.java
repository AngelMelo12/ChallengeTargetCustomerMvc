package com.fiap.challenge.targetcustomer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_TC_CADASTRO")
@Data
@ToString
public class Cadastro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cadastro")
    private Long id;

    @Column(name = "nr_cnpj")
    private Long cnpj;

    @Column(name = "nm_razaosocial")
    @Size(max = 80)
    private String razaoSocial;

    @OneToMany(mappedBy = "cadastro", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<EmailEmpresa> emailEmpresas;

    @OneToMany(mappedBy = "cadastro", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<EnderecoEmpresa> enderecosEmpresas;

    @OneToMany(mappedBy = "cadastro", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<TelefoneEmpresa> telefonesEmpresas;

    public void addEmailEmpresa(EmailEmpresa emailEmpresa) {
        if (this.emailEmpresas == null) {
            emailEmpresas = new ArrayList<>();
        }
        emailEmpresas.add(emailEmpresa);
    }

    public void addEnderecoEmpresa(EnderecoEmpresa enderecoEmpresa) {
        if (this.enderecosEmpresas == null) {
            enderecosEmpresas = new ArrayList<>();
        }
        enderecosEmpresas.add(enderecoEmpresa);
    }

    public void addTelefoneEmpresa(TelefoneEmpresa telefoneEmpresa) {
        if (this.telefonesEmpresas == null) {
            telefonesEmpresas = new ArrayList<>();
        }
        telefonesEmpresas.add(telefoneEmpresa);
    }

}
