Feature: Hello




  Scenario: eagle
       Given I create an eagle
       When I draw an eagle
       Then The eagle has 1 effect
       Then The eagle capacity is "Flying"
       Then The eagle lifePoints is effect
       Then The eagle cost 1 energy

  Scenario: wolf
       Given I create a wolf
       When I draw a wolf
       Then The wolf has 2 effect
       Then the wolf lifePoints is effect
       Then the wolf cost 2 energy


  Scenario: curse
       Given I create a curse
       When i draw a card curse
       Then The curse has 3 effect
       Then the curse cost 3 energy

  Scenario: curseUsed
       Given i have a Curse in my hand
       When i use curse card
       Then the opponent player lose 3 lifepoints

