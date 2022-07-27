package com.activites.api.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;

@Getter
@Setter
@Entity
@Table(name = "jours_ouverture_activite_sur_demande")
public class JoursOuvertureActiviteSurDemande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_activite", nullable = false)
    private ActiviteSurDemande activite;

    private DayOfWeek jourOuverture;


}
