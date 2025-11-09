package com.UPX_IV.mobilizaUrbis.controller;

import com.UPX_IV.mobilizaUrbis.entity.Categoria;
import com.UPX_IV.mobilizaUrbis.entity.Relato;
import com.UPX_IV.mobilizaUrbis.repository.CategoriaRepository;
import com.UPX_IV.mobilizaUrbis.repository.RelatoRepository;
import com.UPX_IV.mobilizaUrbis.service.RelatoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping
    public List<Relato> listarRelatos(@RequestParam(name = "categoriaId", required = false) Long categoriaId){

        System.out.println("categoriaId: "+categoriaId);
        return relatoService.listarRelatos(categoriaId);
    }

    @PatchMapping("/{id}")
    public Relato atualizarStatusRelato(@PathVariable Long id, @RequestBody Map<String, String> body){
        String novoStatus = body.get("status");

       return relatoService.atualizarStatusRelato(id, novoStatus);
    }
}
