Feature: Activité sur demande ouverte la semaine avec reservation samedi suivant pour deux participants

  Scenario: Activité sur demande ouverte la semaine avec reservation samedi suivant pour deux participants
    Given L'activite sur demande de titre "VISITE CHATEAU" et de code "ASDCAEN001" et de contact "contact-chateaux-caen@yahoo.fr" coute 10 euros par participant les
      | SATURDAY |
      | SUNDAY   |
    Then Je verifie que l'activite sur demande de titre "VISITE CHATEAU" et de code "ASDCAEN001" et de contact "contact-chateaux-caen@yahoo.fr" coute 10.00 euros par participant existe les
      | SATURDAY |
      | SUNDAY   |
    Then Je m'attends à payer 20.00 euros pour 2 participants pour l'activite sur demande de code "ASDCAEN001" samedi de la semaine suivante