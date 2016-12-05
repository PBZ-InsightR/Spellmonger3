Feature: Others

  Scenario: Bear vs Fox
    Given two players have no cards
    When the first one have a bear, the second have a fox
    When current attack opponent
    Then bear attack the fox, thus life points didnt changed
  Scenario: Bear vs Bear
    Given two players have no cards
    When both have a bear
    When current attack opponent
    Then both of them are dead,thus life points didnt changed
  Scenario: Bear vs Lizard
    Given two players have no cards
    When the first one have a bear, the second have a lizard
    When current attack opponent
    Then both of them are dead,thus life points didnt changed
  Scenario: Fox vs Bear
    Given two players have no cards
    When the first one have a fox, the second have a bear
    When current attack opponent
    Then nothing happens


  Scenario: Bear vs kraken
    Given two players have no cards
    When first one have an bear,second have a kraken
    When current attack opponent
    Then nothing happens

    #pas sur sur, a voir si c'est bon
  Scenario: Bear vs eagle
    Given two players have no cards
    When first one have a bear,second have an eagle
    When current attack opponent
    Then Attack opponents lifepoints with 3, thus creatures dont change
  Scenario: Fox vs Eagle
    Given two players have no cards
    When first one have a fox,second have an eagle
    When current attack opponent
    Then Attack opponents lifepoints with 1, thus creatures dont change

  Scenario: Bear vs rat
    Given two players have no cards
    When the first one have a bear, the second have a rat
    When current attack opponent
    Then both of them are dead,thus life points didnt changed
  Scenario: fox vs rat
    Given two players have no cards
    When the first one have a fox, the second have a rat
    When current attack opponent
    Then both of them are dead,thus life points didnt changed