package com.activites.api.dto;

import lombok.Data;

import java.time.DayOfWeek;
import java.util.List;

@Data
public class ActiviteVenteLibreDto {

    private String codeActivite;

    private String titre;

    private List<DayOfWeek> joursOuverture;

    private String prixBaseAdulte;

    private String prixBaseEnfant;
}
