package com.UPX_IV.mobilizaUrbis.controller;

import com.UPX_IV.mobilizaUrbis.entity.Relato;
import com.UPX_IV.mobilizaUrbis.service.AdministradorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdministradorController {

    private final AdministradorService administradorService;

    public AdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    // Endpoint: listar todos os relatos
    @GetMapping("/relatos")
    public List<Relato> listarRelatos() {
        return administradorService.listarTodosRelatos();
    }

    // Endpoint: buscar relato por ID
    @GetMapping("/relatos/{id}")
    public Relato buscarRelato(@PathVariable Long id) {
        return administradorService.buscarPorId(id);
    }
}
