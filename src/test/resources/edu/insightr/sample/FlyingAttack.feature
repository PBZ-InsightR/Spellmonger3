Feature: flying

  Scenario: Eagle vs eagle
    Given two players have no cards
    When both of then have an eagle now
    When current attack opponent
    Then both of them are dead
  Scenario: Eagle vs dragon
    Given two players have no cards
    When first one have an eagle,second have a dragon
    When current attack opponent
    Then nothing happens
  Scenario: dragon vs eagle
    Given two players have no cards
    When first one have an dragon,second have a eagle
    When current attack opponent
    Then Eagle died, thus lifePoints didn't changed

  Scenario: eagle vs bear
    Given two players have no cards
    When first one have an eagle,second have a bear
    When current attack opponent
    Then Attack opponents lifepoints with 1, thus creatures dont change
  Scenario: eagle vs fox
    Given two players have no cards
    When first one have an eagle,second have a fox
    When current attack opponent
    Then Attack opponents lifepoints with 1, thus creatures dont change
  Scenario: dragon vs bear
    Given two players have no cards
    When first one have an dragon,second have a bear
    When current attack opponent
    Then Attack opponents lifepoints with 4, thus creatures dont change

  Scenario: eagle vs craken
    Given two players have no cards
    When first one have an eagle,second have a craken
    When current attack opponent
    Then Opponent Eagle died, thus lifePoints didn't changed
  Scenario: dragon vs craken
    Given two players have no cards
    When first one have an dragon,second have a craken
    When current attack opponent
    Then Both died, thus lifePoints didn't changed

  Scenario: eagle vs snake
    Given two players have no cards
    When first one have an eagle,second have a snake
    When current attack opponent
    Then Attack opponents lifepoints with 1, thus creatures dont change
  Scenario: dragon vs snake
    Given two players have no cards
    When first one have an dragon,second have a snake
    When current attack opponent
    Then Attack opponents lifepoints with 4, thus creatures dont change


  Scenario: Eagle,dragon vs dragon
    Given two players have no cards
    When first one have an eagle and dragon, second have a dragon
    When current attack opponent
    Then Dragon attacks the other dragon,and eagle attack the oppenent and cause a damage
  Scenario: Eagle vs dragon,Eagle
    Given two players have no cards
    When first one have an eagle and dragon,second have an eagle
    When current attack opponent
    Then Eagle attacks the eagle, and the dragon attack the person
    # TODO c'est ce qu'il faut qu'on ait
  Scenario: Eagle,dragon vs Eagle,dragon
    Given two players have no cards
    When both have an eagle and dragon
    When current attack opponent
    Then all creatures died, no player is damaged
  Scenario: Eagle,dragon vs Eagle,Eagle
    Given two players have no cards
    When first one have an eagle and dragon,second have two eagle
    When current attack opponent
    Then dragon attacks eagle and beat him,for the second battle, all eagle died, all player arent damaged
  Scenario: Eagle,dragon vs Dragon,Dragon
    Given two players have no cards
    When first one have an eagle and a dragon, the second have two dragons
    When current attack opponent
    Then the eagle dont attack,the second attacks the dragon and both died, all players arent damaged


