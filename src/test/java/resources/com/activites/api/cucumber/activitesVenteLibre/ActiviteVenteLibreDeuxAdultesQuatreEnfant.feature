Feature: Activité vente libre ouverte la semaine pour deux adultes et quatre enfants

  Scenario: Activité vente libre ouverte la semaine pour deux adultes et quatre enfant
    Given L'activite en vente libre de titre "VISITE MUSEE" et de code "AVLCAEN001" coute 20.00 euros par adultes et 10.00 euros par enfants les
      | MONDAY    |
      | TUESDAY   |
      | WEDNESDAY |
      | THURSDAY  |
      | FRIDAY    |
    Then Je verifie que l'activite en vente libre de titre "VISITE MUSEE" et de code "AVLCAEN001" coute 20.00 euros par adultes et 10.00 euros par enfants existe les
      | MONDAY    |
      | TUESDAY   |
      | WEDNESDAY |
      | THURSDAY  |
      | FRIDAY    |
    Then Je m'attends à payer 70.00 euros pour 4 enfants et 2 adultes pour l'activite en vente libre de code "AVLCAEN001" le "2022-07-27"
