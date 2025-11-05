package com.UPX_IV.mobilizaUrbis.service;

import com.UPX_IV.mobilizaUrbis.entity.Relato;
import com.UPX_IV.mobilizaUrbis.repository.RelatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorService {

    private final RelatoRepository relatoRepository;

    public AdministradorService(RelatoRepository relatoRepository) {
        this.relatoRepository = relatoRepository;
    }

    public List<Relato> listarTodosRelatos() {
        return relatoRepository.findAll();
    }

    public Relato buscarPorId(Long id) {
        return relatoRepository.findById(id).orElse(null);
    }
}
