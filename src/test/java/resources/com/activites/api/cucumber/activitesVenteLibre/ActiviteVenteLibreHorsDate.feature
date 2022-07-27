Feature: Activité vente libre deux adultes et un enfant avec reservation hors date

  Scenario: Activité vente libre deux adultes et un enfant avec reservation hors date
    Given L'activite en vente libre de titre "LUNDI VELO" et de code "AVLCAEN003" coute 200.00 euros par adultes et 100.00 euros par enfants les
      | MONDAY |
    Then Je verifie que l'activite en vente libre de titre "LUNDI VELO" et de code "AVLCAEN003" coute 200.00 euros par adultes et 100.00 euros par enfants existe les
      | MONDAY |
    Then Je m'attends à ne pas pouvoir payer pour 1 enfants et 2 adultes pour l'activite en vente libre de code "AVLCAEN003" le "2022-07-27"
