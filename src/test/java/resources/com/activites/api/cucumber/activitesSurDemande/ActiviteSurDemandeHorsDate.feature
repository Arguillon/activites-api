Feature: Activité sur demande ouverte la semaine avec reservation hors date pour cent participants

  Scenario: Activité sur demande ouverte la semaine avec reservation hors date pour cent participants
    Given L'activite sur demande de titre "VISITE PLAGES" et de code "ASDCAEN003" et de contact "contact-plages-caen@yahoo.fr" coute 10 euros par participant les
      | SATURDAY |
      | SUNDAY   |
    Then Je verifie que l'activite sur demande de titre "VISITE PLAGES" et de code "ASDCAEN003" et de contact "contact-plages-caen@yahoo.fr" coute 10.00 euros par participant existe les
      | SATURDAY |
      | SUNDAY   |
    Then Je m'attends à ne pas pouvoir payer pour 100 participants pour l'activite sur demande de code "ASDCAEN003" le "2022-07-27"