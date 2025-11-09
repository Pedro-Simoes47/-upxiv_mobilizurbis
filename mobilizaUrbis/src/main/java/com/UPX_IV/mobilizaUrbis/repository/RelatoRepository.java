package com.UPX_IV.mobilizaUrbis.repository;

import com.UPX_IV.mobilizaUrbis.entity.Relato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelatoRepository extends JpaRepository<Relato,Long> {

    List<Relato> findByCategoriaId(Long categoriaId);

}
