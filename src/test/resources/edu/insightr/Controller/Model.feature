Feature: Models
  Scenario: intialistion of the game
    Given the game is lauched
    Then they have both 20 life points, 0 energy, and 3 cards in hands

  Scenario: more than 5 cards
    Given the game is lauched
    When a player have 5 cards in hand
    When  he wanna draw another one
    Then he cannot draw another card

  Scenario: more than 10 energy
    Given the game is lauched
    When a player who  has 10 energy
    When  it's his turn, he wanna gain another energy
    Then he cannot gain another energy

  Scenario: energy drain
    Given the game is lauched
    When a player who  has 10 energy
    When  he play  an energy drain
    Then he have 12 energy now


