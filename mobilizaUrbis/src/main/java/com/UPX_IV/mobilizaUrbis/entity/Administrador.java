package com.UPX_IV.mobilizaUrbis.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "administradores")
@Data
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;
}
