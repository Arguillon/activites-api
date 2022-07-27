package com.activites.api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.List;

@Data
public class ActiviteSurDemandeDto {

    private String codeActivite;

    private String titre;

    private List<DayOfWeek> joursOuverture;

    private BigDecimal prixFixeParticipant;

    private String emailContact;
}
