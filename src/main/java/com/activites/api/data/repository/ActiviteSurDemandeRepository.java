package com.activites.api.data.repository;

import com.activites.api.data.entity.ActiviteSurDemande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActiviteSurDemandeRepository extends JpaRepository<ActiviteSurDemande, Integer> {
    Optional<ActiviteSurDemande> findByCodeActivite(String codeActivite);

}
