package com.UPX_IV.mobilizaUrbis.service;

import com.UPX_IV.mobilizaUrbis.entity.Categoria;
import com.UPX_IV.mobilizaUrbis.entity.Relato;
import com.UPX_IV.mobilizaUrbis.repository.CategoriaRepository;
import com.UPX_IV.mobilizaUrbis.repository.RelatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;


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
        novoRelato.setStatus("Não Iniciado!");
        novoRelato.setDataCriacao(java.time.LocalDateTime.now());
        return  relatoRepository.save(novoRelato);
    }

    public List<Relato> listarRelatos(Long categoriaId) {
        if (categoriaId == null) {
            return relatoRepository.findAll();
        } else {
            return relatoRepository.findByCategoriaId(categoriaId);
        }
    }

    public Relato atualizarStatusRelato(Long idRelato, String novoStatus) {
        Relato relato =  relatoRepository.findById(idRelato).orElseThrow(() -> new RuntimeException("Relato Nao Encontrado"));
        relato.setStatus(novoStatus);
        return relatoRepository.save(relato);
    }
}
