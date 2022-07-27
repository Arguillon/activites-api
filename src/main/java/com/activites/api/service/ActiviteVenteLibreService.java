package com.activites.api.service;

import com.activites.api.data.entity.ActiviteVenteLibre;
import com.activites.api.data.repository.ActiviteVenteLibreRepository;
import com.activites.api.mapper.ActiviteVenteLibreModelMapper;
import com.activites.api.model.ActiviteVenteLibreModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActiviteVenteLibreService {

    private final ActiviteVenteLibreRepository activiteVenteLibreRepository;
    private final ActiviteVenteLibreModelMapper activiteVenteLibreModelMapper;

    public Optional<ActiviteVenteLibre> findByActiviteId(Integer idActivite) {
        return activiteVenteLibreRepository.findById(idActivite);
    }

    /**
     * Calcul du prix d'une activité en vente libre en fonction de
     *
     * @param idActivite    l'identifiant technique de l'activité
     * @param nbAdultes     le nombre d'adultes dans la demande
     * @param nbEnfants     le nombre d'enfants dans la demande
     * @param dateSouhaitee la date souhaitée pour la demande d'activité
     * @return null si la date demandée n'est pas incluse dans les dates d'ouverture de l'activité, un prix sinon
     */
    public BigDecimal calculPrixActiviteVenteLibre(int idActivite, Integer nbAdultes, Integer nbEnfants, LocalDate dateSouhaitee) {
        BigDecimal prix = null;

        Optional<ActiviteVenteLibre> oActivite = findByActiviteId(idActivite);
        if (oActivite.isPresent()) {
            ActiviteVenteLibreModel activiteModel = activiteVenteLibreModelMapper.toDestination(oActivite.get());

            if (activiteModel.getJoursOuverture().contains(dateSouhaitee.getDayOfWeek())) {
                BigDecimal prixEnfant = calculPrixEnfant(nbEnfants, activiteModel);
                prix = prixEnfant.add(activiteModel.getPrixBaseAdulte().multiply(BigDecimal.valueOf(nbAdultes)));
            }
        }

        return prix;

    }

    /**
     * Calcul du prix de l'activité en fonction du nombre d'enfants
     * Pour les activités en vente libre, le prix de base par enfant réduit de moitié pour le 3ᵉ enfant et +
     *
     * @param nbEnfants     le nombre d'enfants dans la demande de prix
     * @param activiteModel l'activité
     * @return 0 si aucun enfant, un prix sinon
     */
    private BigDecimal calculPrixEnfant(Integer nbEnfants, ActiviteVenteLibreModel activiteModel) {
        BigDecimal prixEnfant = BigDecimal.ZERO;

        for (int i = 0; i < nbEnfants; i++) {
            if (i < 2) {
                prixEnfant = prixEnfant.add(activiteModel.getPrixBaseEnfant());
            } else {
                prixEnfant = prixEnfant.add(activiteModel.getPrixBaseEnfant().divide(BigDecimal.valueOf(2), RoundingMode.HALF_UP));
            }
        }
        return prixEnfant;
    }
}
