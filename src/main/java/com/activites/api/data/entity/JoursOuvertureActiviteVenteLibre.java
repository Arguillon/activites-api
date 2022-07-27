package com.activites.api.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;

@Getter
@Setter
@Entity
@Table(name = "jours_ouverture_activite_vente_libre")
public class JoursOuvertureActiviteVenteLibre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_activite", nullable = false)
    private ActiviteVenteLibre activite;

    private DayOfWeek jourOuverture;


}
