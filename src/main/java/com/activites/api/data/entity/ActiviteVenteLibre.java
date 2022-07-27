package com.activites.api.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "activite_vente_libre")
public class ActiviteVenteLibre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "code_activite", nullable = false)
    private String codeActivite;

    @Column(name = "titre", nullable = false)
    private String titre;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "activite")
    private List<JoursOuvertureActiviteVenteLibre> joursOuverture;

    @Column(name = "prix_base_adulte", nullable = false)
    private BigDecimal prixBaseAdulte;

    @Column(name = "prix_base_enfant", nullable = false)
    private BigDecimal prixBaseEnfant;
}
