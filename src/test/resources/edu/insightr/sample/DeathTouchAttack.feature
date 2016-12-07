Feature: DeathTouch

  Scenario: Rat vs Snake
    Given two players have no cards
    When the first one have a rat, the second have a snake
    When current attack opponent
    Then both of them are dead


  Scenario: snake vs bear
    Given two players have no cards
    When first one have a snake,second have a bear
    When current attack opponent
    Then both of them are dead
  Scenario: snake vs fox
    Given two players have no cards
    When first one have a snake,second have a fox
    When current attack opponent
    Then both of them are dead

  Scenario: snake vs craken
    Given two players have no cards
    When first one have a snake,second have a craken
    When current attack opponent
    Then both of them are dead

  Scenario: snake vs eagle
    Given two players have no cards
    When first one have a snake,second have an eagle
    When current attack opponent
    Then both of them are dead
  Scenario: snake vs dragon
    Given two players have no cards
    When first one have a snake,second have a dragon
    When current attack opponent
    Then both of them are dead

