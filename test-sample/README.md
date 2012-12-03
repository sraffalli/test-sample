test-sample
===========

## Hands on "Unit tests" (3 hours)

### TDD experiment (1h30)

* creation of simple unit tests
* discover [EclEmma](http://www.eclemma.org/) and [MoreUnit](http://moreunit.sourceforge.net/) eclipe plugins
* use of mocks with [Mockito](http://code.google.com/p/mockito/)


### Integration tests (1h30)

* integration tests with [Spring-test](http://static.springsource.org/spring/docs/3.2.0.RC1/reference/htmlsingle/#testcontext-framework)
* parameterized tests
* @Category and segmentation of test executions


## Contracts

### Cards
* Have a Value (2,3,..10,J,Q,K,1) and a Color (Hearts, Spades, Clubs, Diamonds)
* Two cards with the same value and the same color are equal

### Deck 
* initialize a deck of 32 cards
* shuffle the deck
* distribute a card one by one

### Player
* add a card into his hand
* choose a card to play
* reset his hand

### Rules
* specify which cards a player can play at his round
* say if the game is finished or not
* determine the winner

### "My american's 8" Rules

1. Distribute all the cards one by one to each player
2. The first player plays one card of his choice
3. Then, by turns, each player can play a card with the same value or the same color as the previous card. If he can't play, he takes all played cards into his hand, and chooses a new card to play.
4. The first player who plays his last card wins the game; or if no player can play a card anymore, the winner is the player with the less cards in his hand.


