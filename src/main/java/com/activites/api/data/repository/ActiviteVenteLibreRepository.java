package com.activites.api.data.repository;

import com.activites.api.data.entity.ActiviteVenteLibre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActiviteVenteLibreRepository extends JpaRepository<ActiviteVenteLibre, Integer> {
    Optional<ActiviteVenteLibre> findByCodeActivite(String codeActivite);
}
