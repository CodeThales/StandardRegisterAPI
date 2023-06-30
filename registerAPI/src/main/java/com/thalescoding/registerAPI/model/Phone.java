package com.thalescoding.registerAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="t_phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String phoneNumber;

    //@ManyToOne = Muitos telefones para um user com optional = false para garantir que seja obrigatório um user associado ao telefone|
    //@JoinColumn(name = "user_id") referência FK |
    //@JsonIgnore = Informa o Spring para não entrar no User na conversão para JSON quando o Phone é chamado dentro do
    //user, caso contrário ele entra em looping gerando stack overflow. Em suma, essa anotação resolve um problema de recursividade
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Phone() {
    }

    public Phone(Long id, String phoneNumber, User user) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
