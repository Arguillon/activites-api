Feature: Activité sur demande ouverte la semaine avec reservation dimanche le plus proche pour deux participants

  Scenario: Activité sur demande ouverte la semaine avec reservation dimanche le plus proche pour deux participants
    Given L'activite sur demande de titre "VISITE PORT" et de code "ASDCAEN002" et de contact "contact-port-caen@yahoo.fr" coute 10 euros par participant les
      | SATURDAY |
      | SUNDAY   |
    Then Je verifie que l'activite sur demande de titre "VISITE PORT" et de code "ASDCAEN002" et de contact "contact-port-caen@yahoo.fr" coute 10.00 euros par participant existe les
      | SATURDAY |
      | SUNDAY   |
    Then Je m'attends à payer 11.00 euros pour 1 participants pour l'activite sur demande de code "ASDCAEN002" dimanche le plus proche