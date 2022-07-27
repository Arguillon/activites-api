package com.activites.api.data.repository;

import com.activites.api.data.entity.JoursOuvertureActiviteSurDemande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoursOuvertureActiviteSurDemandeRepository extends JpaRepository<JoursOuvertureActiviteSurDemande, Integer> {

}
