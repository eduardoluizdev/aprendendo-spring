package com.eduardo.aprendendospring.infrastructure.repository;

import com.eduardo.aprendendospring.infrastructure.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Usuario, Long> {
}
