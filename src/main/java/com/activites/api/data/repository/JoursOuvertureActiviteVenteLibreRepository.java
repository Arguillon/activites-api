package com.activites.api.data.repository;

import com.activites.api.data.entity.JoursOuvertureActiviteVenteLibre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoursOuvertureActiviteVenteLibreRepository extends JpaRepository<JoursOuvertureActiviteVenteLibre, Integer> {

}
