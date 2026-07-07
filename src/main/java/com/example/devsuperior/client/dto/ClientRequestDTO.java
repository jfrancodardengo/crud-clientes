package com.example.devsuperior.client.dto;

import com.example.devsuperior.client.model.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ClientRequestDTO {
    @NotBlank(message = "O nome é obrigatório e não pode conter apenas espaços.")
    @Size(min = 3, max = 80, message = "O nome deve ter entre {min} e {max} caracteres.")
    private String name;

    @NotBlank(message = "O cpf é obrigatório.")
    private String cpf;

    @NotNull(message = "A renda é obrigatória.")
    private Double income;

    @NotNull(message = "A data de nascimento é obrigatória.")
    @PastOrPresent(message = "A data de nascimento não pode ser data futura.")
    private LocalDate birthDate;

    private Integer children;

    public ClientRequestDTO() {
    }

    public ClientRequestDTO(String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClientRequestDTO(Client client) {
        name = client.getName();
        cpf = client.getCpf();
        income = client.getIncome();
        birthDate = client.getBirthDate();
        children = client.getChildren();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }
}
