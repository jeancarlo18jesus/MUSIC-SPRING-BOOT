package com.edu.edu_musica_aa2.models.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class Usuario implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @NotEmpty(message = "No puede estar vacio")
    @Column(name = "username" , unique = true)
    private String username ;

    @NotEmpty(message = "No puede estar vacio")
    @Column(name = "password")
    private String password ;

    @Column(name = "enabled")
    private int enabled ;
    // @OneToMany
    // @JoinColumn(name = "user_id")
    // private List<Rol> roles ;

}
