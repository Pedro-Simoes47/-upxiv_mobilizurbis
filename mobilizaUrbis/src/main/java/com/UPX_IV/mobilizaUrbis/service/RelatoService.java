package com.UPX_IV.mobilizaUrbis.service;

import com.UPX_IV.mobilizaUrbis.entity.Categoria;
import com.UPX_IV.mobilizaUrbis.entity.Relato;
import com.UPX_IV.mobilizaUrbis.repository.CategoriaRepository;
import com.UPX_IV.mobilizaUrbis.repository.RelatoRepository;
import org.springframework.stereotype.Service;


@Service
public class RelatoService {

    RelatoRepository relatoRepository;
    CategoriaRepository categoriaRepository;

    public RelatoService(RelatoRepository relatoRepository, CategoriaRepository categoriaRepository) {
        this.relatoRepository = relatoRepository;
        this.categoriaRepository = categoriaRepository;
    }


    public Relato criarRelato(Relato novoRelato){

        Long idCategoria = novoRelato.getCategoria().getId();

        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoria nao encontrada"));

        novoRelato.setCategoria(categoria);

        return  relatoRepository.save(novoRelato);
    }
}
