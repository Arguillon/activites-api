package com.activites.api.cucumber.steps;

import com.activites.api.data.entity.ActiviteSurDemande;
import com.activites.api.data.entity.JoursOuvertureActiviteSurDemande;
import com.activites.api.data.repository.ActiviteSurDemandeRepository;
import com.activites.api.data.repository.JoursOuvertureActiviteSurDemandeRepository;
import com.activites.api.service.ActiviteSurDemandeService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Data
public class ActiviteSurDemandeStep {

    private final ActiviteSurDemandeRepository activiteSurDemandeRepository;
    private final JoursOuvertureActiviteSurDemandeRepository joursOuvertureActiviteSurDemandeRepository;
    private final ActiviteSurDemandeService activiteSurDemandeService;


    @Given("L'activite sur demande de titre {string} et de code {string} et de contact {string} coute {double} euros par participant les")
    public void creationActiviteSurDemande(String titre, String code, String mailContact, double prixParticipant, DataTable table) {

        ActiviteSurDemande activite = new ActiviteSurDemande();
        activite.setCodeActivite(code);
        activite.setTitre(titre);
        activite.setPrixFixeParticipant(new BigDecimal(prixParticipant));
        activite.setEmailContact(mailContact);
        activite = activiteSurDemandeRepository.save(activite);
        activite.setJoursOuverture(new ArrayList<>());

        for (String jour : table.asList()) {
            JoursOuvertureActiviteSurDemande joursOuvertureActiviteSurDemande = new JoursOuvertureActiviteSurDemande();
            joursOuvertureActiviteSurDemande.setActivite(activite);
            joursOuvertureActiviteSurDemande.setJourOuverture(DayOfWeek.valueOf(jour));
            joursOuvertureActiviteSurDemande = joursOuvertureActiviteSurDemandeRepository.save(joursOuvertureActiviteSurDemande);
            activite.getJoursOuverture().add(joursOuvertureActiviteSurDemande);
        }
    }

    @Then("Je verifie que l'activite sur demande de titre {string} et de code {string} et de contact {string} coute {double} euros par participant existe les")
    public void verificationCreationActiviteSurDemande(String titre, String code, String mailContact, double prixParticipant, DataTable table) {
        Optional<ActiviteSurDemande> oActivite = activiteSurDemandeRepository.findByCodeActivite(code);
        Assertions.assertTrue(oActivite.isPresent());
        SoftAssertions softly = new SoftAssertions();
        ActiviteSurDemande activite = oActivite.get();
        softly.assertThat(code).isEqualTo(activite.getCodeActivite());
        softly.assertThat(titre).isEqualTo(activite.getTitre());
        softly.assertThat(mailContact).isEqualTo(activite.getEmailContact());
        softly.assertThat(new BigDecimal(prixParticipant).compareTo(activite.getPrixFixeParticipant())).isEqualTo(0);
        softly.assertThat(table.asList().stream().map(DayOfWeek::valueOf).collect(Collectors.toList()))
                .isEqualTo(activite.getJoursOuverture().stream().map(JoursOuvertureActiviteSurDemande::getJourOuverture).toList());
        softly.assertAll();
    }

    @Then("Je m'attends à payer {double} euros pour {int} participant(s) pour l'activite sur demande de code {string} samedi de la semaine suivante")
    public void testTarifActiviteSurDemandeSamediSemaineSuivante(double prixEstime, int nbParticipants, String codeActivite) {
        LocalDate dateSouhaitee = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY));

        // On vient forcer à récupérer le samedi de la semaine suivante
        if (dateSouhaitee.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR) == LocalDate.now().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR)) {
            dateSouhaitee = dateSouhaitee.plusDays(7);
        }
        Optional<ActiviteSurDemande> oActivite = activiteSurDemandeRepository.findByCodeActivite(codeActivite);
        Assertions.assertTrue(oActivite.isPresent());

        int idActivite = oActivite.get().getId();

        BigDecimal prixTotal = activiteSurDemandeService.calculPrixActiviteSurDemande(idActivite, nbParticipants, dateSouhaitee);

        Assertions.assertEquals(0, new BigDecimal(prixEstime).compareTo(prixTotal));
    }

    @Then("Je m'attends à payer {double} euros pour {int} participant(s) pour l'activite sur demande de code {string} dimanche le plus proche")
    public void testTarifActiviteSurDemandeDimanchePlusProche(double prixEstime, Integer nbParticipants, String codeActivite) {
        LocalDate dateSouhaitee = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY));

        Optional<ActiviteSurDemande> oActivite = activiteSurDemandeRepository.findByCodeActivite(codeActivite);
        Assertions.assertTrue(oActivite.isPresent());

        int idActivite = oActivite.get().getId();

        BigDecimal prixTotal = activiteSurDemandeService.calculPrixActiviteSurDemande(idActivite, nbParticipants, dateSouhaitee);

        Assertions.assertEquals(0, new BigDecimal(prixEstime).compareTo(prixTotal));
    }

    @Then("Je m'attends à ne pas pouvoir payer pour {int} participant(s) pour l'activite sur demande de code {string} le {string}")
    public void testTarifActiviteHorsDate(Integer nbParticipants, String codeActivite, String date) {
        LocalDate dateSouhaitee = LocalDate.parse(date);
        Optional<ActiviteSurDemande> oActivite = activiteSurDemandeRepository.findByCodeActivite(codeActivite);
        Assertions.assertTrue(oActivite.isPresent());

        int idActivite = oActivite.get().getId();

        BigDecimal prixTotal = activiteSurDemandeService.calculPrixActiviteSurDemande(idActivite, nbParticipants, dateSouhaitee);

        Assertions.assertNull(prixTotal);
    }

}
