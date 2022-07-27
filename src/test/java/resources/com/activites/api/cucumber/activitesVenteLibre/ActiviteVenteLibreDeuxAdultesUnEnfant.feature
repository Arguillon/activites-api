Feature: Activité vente libre ouverte la semaine pour deux adultes et un enfant

  Scenario: Activité vente libre deux adultes et un enfant
    Given L'activite en vente libre de titre "CONCERT CARGO" et de code "AVLCAEN002" coute 20.00 euros par adultes et 10.00 euros par enfants les
      | MONDAY    |
      | TUESDAY   |
      | WEDNESDAY |
    Then Je verifie que l'activite en vente libre de titre "CONCERT CARGO" et de code "AVLCAEN002" coute 20.00 euros par adultes et 10.00 euros par enfants existe les
      | MONDAY    |
      | TUESDAY   |
      | WEDNESDAY |
    Then Je m'attends à payer 50.00 euros pour 1 enfants et 2 adultes pour l'activite en vente libre de code "AVLCAEN002" le "2022-07-27"
