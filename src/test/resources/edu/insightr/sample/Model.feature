Feature: model
//Animals
  Scenario: Bear
    Given I create a Bear
    When I draw a card
    Then the bear has 3 effect
    Then the bear has 3 life
    Then the bear cost 3 energies

    Scenario: Fox
      Given I create a Fox
      When I draw a card Fox
      Then The fox has 1 effect
      Then The fox has 1 life
      Then The fox cost 1 energies

      Scenario: Blessing
        Given I create a Blessing
        When I draw a card Blessing
        Then The blessing has 3 effect
        Then The blessing cost 3 energies

        Scenario: BlessingUsed
          Given I have a Blessing in my hand
          When I use Blessing card
          Then The current player earn 3 lifepoints

          Scenario: EnergyDrain
            Given I create a EnergyDrain
            When I draw a EnergyDrain card
            Then The EnergyDrain card has 2 effect
            Then The EnergyDrain card cost 3 energies

            Scenario: EnergyDrainUsed
              Given I have an EnergyDrain card in my hand
              When I use an EnergyDrain card
              Then The opponent lose 2 energies
              Then The current player earn 2 energies

                Scenario: VaultOverclocking
                  Given I create a VaultOverclocking card
                  When I draw a card VaultOverclocking
                  Then The VaultOverclocking card cost 4 energies
                  Then The VaultOverclocking card is not active

                  Scenario: VaultOverclockingUsed
                    Given I have a VaultOverclocking card in my hand
                    When I use a VaultOverclocking card
                    Then The current player energy is set to 1 or his energy is increases by 1

                    Scenario: ControllerPlay
                      Given The game began
                      When The players are connected
                      Then The name of the first player is "Player1"
                      Then The name of the second player is "Player2"
                      Then The players have 20 life points
                      Then The players have 0 energy
                      Then The player have 2 cards in their hand

                      Scenario: DrawCard
                        Given Two players are playing
                        When The Players draw a card
                        Then The number of card of the players increases by 1