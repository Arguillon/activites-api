package com.activites.api.service;

import com.activites.api.data.entity.ActiviteSurDemande;
import com.activites.api.data.repository.ActiviteSurDemandeRepository;
import com.activites.api.mapper.ActiviteSurDemandeModelMapper;
import com.activites.api.model.ActiviteSurDemandeModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.IsoFields;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActiviteSurDemandeService {

    private final ActiviteSurDemandeRepository activiteSurDemandeRepository;
    private final ActiviteSurDemandeModelMapper activiteSurDemandeModelMapper;

    public Optional<ActiviteSurDemande> findByActiviteId(Integer idActivite) {
        return activiteSurDemandeRepository.findById(idActivite);
    }

    /**
     * Calcul du prix d'une activité sur demande en fonction de
     *
     * @param idActivite          l'identifiant technique de l'activité
     * @param nbTotalParticipants le nombre total de participants
     * @param dateSouhaitee       la date souhaitée pour la demande d'activité
     * @return null si la date demandée n'est pas incluse dans les dates d'ouverture de l'activité, un prix sinon
     */
    public BigDecimal calculPrixActiviteSurDemande(Integer idActivite, int nbTotalParticipants, LocalDate dateSouhaitee) {
        BigDecimal prix = null;

        Optional<ActiviteSurDemande> oActivite = findByActiviteId(idActivite);
        if (oActivite.isPresent()) {
            ActiviteSurDemandeModel activiteModel = activiteSurDemandeModelMapper.toDestination(oActivite.get());

            if (activiteModel.getJoursOuverture().contains(dateSouhaitee.getDayOfWeek())) {
                prix = activiteModel.getPrixFixeParticipant().multiply(BigDecimal.valueOf(nbTotalParticipants));


                if (dateSouhaitee.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR) == LocalDate.now().get(ChronoField.ALIGNED_WEEK_OF_YEAR)) {
                    prix = prix.multiply(BigDecimal.valueOf(1.1));
                }
            }
        }
        return prix;
    }
}
