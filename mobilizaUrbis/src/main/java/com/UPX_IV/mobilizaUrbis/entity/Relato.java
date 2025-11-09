package com.UPX_IV.mobilizaUrbis.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "relatos")
@Data
@Getter
@Setter
public class Relato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private String localizacao;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private LocalDateTime dataCriacao;
    private String fotoURL;
    private String status = "Não iniciado.";

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;


}



