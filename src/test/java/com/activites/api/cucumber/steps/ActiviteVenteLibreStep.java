package com.activites.api.cucumber.steps;

import com.activites.api.data.entity.ActiviteVenteLibre;
import com.activites.api.data.entity.JoursOuvertureActiviteVenteLibre;
import com.activites.api.data.repository.ActiviteVenteLibreRepository;
import com.activites.api.data.repository.JoursOuvertureActiviteVenteLibreRepository;
import com.activites.api.service.ActiviteVenteLibreService;
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
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Data
public class ActiviteVenteLibreStep {

    private final ActiviteVenteLibreRepository activiteVenteLibreRepository;
    private final JoursOuvertureActiviteVenteLibreRepository joursOuvertureActiviteVenteLibreRepository;
    private final ActiviteVenteLibreService activiteVenteLibreService;


    @Given("L'activite en vente libre de titre {string} et de code {string} coute {double} euros par adultes et {double} euros par enfants les")
    public void creationActivite(String titre, String code, double prixAdulte, double prixEnfant, DataTable table) {

        ActiviteVenteLibre activite = new ActiviteVenteLibre();
        activite.setCodeActivite(code);
        activite.setTitre(titre);
        activite.setPrixBaseAdulte(new BigDecimal(prixAdulte));
        activite.setPrixBaseEnfant(new BigDecimal(prixEnfant));
        activite = activiteVenteLibreRepository.save(activite);
        activite.setJoursOuverture(new ArrayList<>());

        for (String jour : table.asList()) {
            JoursOuvertureActiviteVenteLibre joursOuvertureActiviteVenteLibre = new JoursOuvertureActiviteVenteLibre();
            joursOuvertureActiviteVenteLibre.setActivite(activite);
            joursOuvertureActiviteVenteLibre.setJourOuverture(DayOfWeek.valueOf(jour));
            joursOuvertureActiviteVenteLibre = joursOuvertureActiviteVenteLibreRepository.save(joursOuvertureActiviteVenteLibre);
            activite.getJoursOuverture().add(joursOuvertureActiviteVenteLibre);
        }
    }

    @Then("Je verifie que l'activite en vente libre de titre {string} et de code {string} coute {double} euros par adultes et {double} euros par enfants existe les")
    public void verificationCreationActivite(String titre, String code, double prixAdulte, double prixEnfant, DataTable table) {
        Optional<ActiviteVenteLibre> oActivite = activiteVenteLibreRepository.findByCodeActivite(code);
        Assertions.assertTrue(oActivite.isPresent());
        SoftAssertions softly = new SoftAssertions();
        ActiviteVenteLibre activite = oActivite.get();
        softly.assertThat(code).isEqualTo(activite.getCodeActivite());
        softly.assertThat(titre).isEqualTo(activite.getTitre());
        softly.assertThat(new BigDecimal(prixAdulte).compareTo(activite.getPrixBaseAdulte())).isEqualTo(0);
        softly.assertThat(new BigDecimal(prixEnfant).compareTo(activite.getPrixBaseEnfant())).isEqualTo(0);
        softly.assertThat(table.asList().stream().map(DayOfWeek::valueOf).collect(Collectors.toList()))
                .isEqualTo(activite.getJoursOuverture().stream().map(JoursOuvertureActiviteVenteLibre::getJourOuverture).toList());
        softly.assertAll();
    }

    @Then("Je m'attends à payer {double} euros pour {int} enfant(s) et {int} adulte(s) pour l'activite en vente libre de code {string} le {string}")
    public void testTarifActivite(double prixEstime, Integer nbEnfants, Integer nbAdultes, String codeActivite, String date) {
        LocalDate dateSouhaitee = LocalDate.parse(date);
        Optional<ActiviteVenteLibre> oActivite = activiteVenteLibreRepository.findByCodeActivite(codeActivite);
        Assertions.assertTrue(oActivite.isPresent());
        int idActivite = oActivite.get().getId();

        BigDecimal prixTotal = activiteVenteLibreService.calculPrixActiviteVenteLibre(idActivite, nbAdultes, nbEnfants, dateSouhaitee);

        Assertions.assertEquals(0, new BigDecimal(prixEstime).compareTo(prixTotal));
    }

    @Then("Je m'attends à ne pas pouvoir payer pour {int} enfant(s) et {int} adulte(s) pour l'activite en vente libre de code {string} le {string}")
    public void testTarifActiviteHorsDate(Integer nbEnfants, Integer nbAdultes, String codeActivite, String date) {
        LocalDate dateSouhaitee = LocalDate.parse(date);
        Optional<ActiviteVenteLibre> oActivite = activiteVenteLibreRepository.findByCodeActivite(codeActivite);
        Assertions.assertTrue(oActivite.isPresent());

        int idActivite = oActivite.get().getId();

        BigDecimal prixTotal = activiteVenteLibreService.calculPrixActiviteVenteLibre(idActivite, nbAdultes, nbEnfants, dateSouhaitee);

        Assertions.assertNull(prixTotal);
    }
}
