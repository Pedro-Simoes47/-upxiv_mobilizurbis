package com.UPX_IV.mobilizaUrbis.controller;

import com.UPX_IV.mobilizaUrbis.entity.Categoria;
import com.UPX_IV.mobilizaUrbis.entity.Relato;
import com.UPX_IV.mobilizaUrbis.repository.CategoriaRepository;
import com.UPX_IV.mobilizaUrbis.repository.RelatoRepository;
import com.UPX_IV.mobilizaUrbis.service.RelatoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/relatos")
public class RelatoController {

    private RelatoService relatoService;

    public RelatoController(RelatoService relatoService) {
        this.relatoService = relatoService;
    }

    @PostMapping
    public Relato criarRelato(@RequestBody Relato novoRelato){
        return relatoService.criarRelato(novoRelato);
    }
}
