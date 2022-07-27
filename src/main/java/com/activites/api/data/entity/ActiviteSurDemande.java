package com.activites.api.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "activite_sur_demande")
public class ActiviteSurDemande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "code_activite", nullable = false)
    private String codeActivite;

    @Column(name = "titre", nullable = false)
    private String titre;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "activite")
    private List<JoursOuvertureActiviteSurDemande> joursOuverture;

    @Column(name = "prix_fixe_participant", nullable = false)
    private BigDecimal prixFixeParticipant;

    @Column(name = "email_contact", nullable = false)
    private String emailContact;
}
