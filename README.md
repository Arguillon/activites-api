# activites-api
Test de création de programme pour calcul de coût d'activités

# Objectif

Votre entreprise, un site e-commerce qui vend des activités touristiques, vous demande de créer un programme devant permettre de récupérer le prix de différentes activités sur le mois courant.

Dans un premier temps, ce programme devra gérer deux types d'activités :

A) Les activités en vente libre, qui ont les propriétés suivantes :
- Code de l'activité
- Titre
- Jours d'ouverture (jours de la semaine)
- Un prix de base par adulte et un prix de base par enfant

B) Les activités en vente sur demande, qui ont les propriétés suivantes :
- Code de l'activité
- Titre
- Jours d'ouverture (jours de la semaine)
- Un prix fixe par participant
- Adresse e-mail de contact

Le programme sera étendu par la suite avec d'autres types d'activités (activité en API, activités qui disposent d'un stock fini de billets, ...)

Par ailleurs, le calcul du prix de l'activité devra respecter les règles de gestion suivantes :
- Le prix est indéfini sur les jours où l'activité est indisponible
- Pour les activités en vente libre :
  --> un prix de base par enfant réduit de moitié pour le 3ᵉ enfant et +
- Pour les activités en vente sur demande :
  --> un prix par participant 10% plus cher pour une activité dans les sept prochains jours


Exemples pour bien illustrer :

Pour une activité en vente libre, qui a un prix de base de 20€ par adulte et 10€ par enfant et qui est ouverte uniquement en semaine,
- le prix d'une réservation pour deux adultes et un enfant pour le lundi est de 20+20+10=50€
- le prix d'une réservation pour deux adultes et quatre enfants pour le vendredi est de 20+20+10+10+5+5=70€
- le prix d'une réservation, peu importe le nombre de participants, est indéfini pour un dimanche

Pour une activité en vente sur demande, qui a un prix par participant de 10€ et qui est ouverte uniquement le week-end,
- le prix d'une réservation pour deux participants pour le samedi de la semaine prochaine est de 10+10=20€
- le prix d'une réservation pour un participant pour le dimanche le plus proche est de 10*1.1=11€
- le prix d'une réservation, peu importe le nombre de participants, est indéfini pour un lundi
