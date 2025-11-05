package com.UPX_IV.mobilizaUrbis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.UPX_IV.mobilizaUrbis.entity.Administrador;
import java.util.Optional;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    Optional<Administrador> findByEmail(String email);
}
