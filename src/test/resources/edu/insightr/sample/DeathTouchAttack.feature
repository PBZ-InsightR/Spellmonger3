Feature: DeathTouch

  Scenario: Rat vs Snake
    Given two players have no cards
    When the first one have a rat, the second have a snake
    When current attack opponent
    Then both of them are dead


  Scenario: snake vs bear
    Given two players have no cards
    When first one have an eagle,second have a bear
    When current attack opponent
    Then Attack opponents lifepoints with 1, thus creatures dont change
  Scenario: snake vs fox
    Given two players have no cards
    When first one have an eagle,second have a fox
    When current attack opponent
    Then Attack opponents lifepoints with 1, thus creatures dont change

  Scenario: snake vs craken
    Given two players have no cards
    When first one have an eagle,second have a craken
    When current attack opponent
    Then Opponent Eagle died, thus lifePoints didn't changed

  Scenario: snake vs eagle
    Given two players have no cards
    When first one have an eagle,second have a snake
    When current attack opponent
    Then Attack opponents lifepoints with 1, thus creatures dont change
  Scenario: snake vs dragon
    Given two players have no cards
    When first one have an dragon,second have a snake
    When current attack opponent
    Then Attack opponents lifepoints with 4, thus creatures dont change

