Feature: IA
  Scenario: 2 creatures vs nothing
    Given a player VS IA
    When IA have 3 energy
    When component have nothing in the game, and IA have a fox and bear in his hand
    Then IA plays the bear

  Scenario: 2 creatures vs nothing
    Given a player VS IA
    When IA have 1 energy
    When component have nothing in the game, and IA have a fox and bear in his hand
    Then IA plays the fox

  Scenario: Eagle and bear in hand vs dragon
    Given a player VS IA
    When IA have 3 energy
    When component have a dragon in his creatures, and IA have a eagle and bear in his hand
    Then IA plays the eagle


  Scenario: Energy drain vs bear
    Given a player VS IA
    When IA have 3 energy
    When component have a bear in his creatures, and IA have energy drain in his hand
    Then IA plays the energy drain


  Scenario: Energy drain vs bear
    Given a player VS IA
    When IA have 3 energy
    When component have a bear in his creatures, and IA have energy drain in his hand
    Then IA plays the energy drain


  Scenario: Fox vs bear LV1
    Given a player VS IA
    When IA have 3 energy
    When component have a bear in his creatures, and IA have fox and bear in his hand
    Then IA plays the fox