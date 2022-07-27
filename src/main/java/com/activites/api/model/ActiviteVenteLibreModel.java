package com.activites.api.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.List;

@Data
public class ActiviteVenteLibreModel {

    private String codeActivite;

    private String titre;

    private List<DayOfWeek> joursOuverture;

    private BigDecimal prixBaseAdulte;

    private BigDecimal prixBaseEnfant;
}
