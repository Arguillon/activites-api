package com.activites.api.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.List;

@Data
public class ActiviteSurDemandeModel {


    private String codeActivite;

    private String titre;

    private List<DayOfWeek> joursOuverture;

    private BigDecimal prixFixeParticipant;

    private String emailContact;
}
