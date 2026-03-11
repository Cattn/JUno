# Release Notes

## Current Implemented Features

### Game setup
- Command-line UNO-style game written in Java.
- Supports 2 to 10 players.
- Prompts for each player's name before the match begins.
- Assigns a default name in the format `Player N` when a name is left blank.
- Clears the console between major phases to keep turns private.
- Uses platform-aware screen clearing for Windows and non-Windows terminals.

### Deck and dealing
- Builds a shuffled draw deck at game start.
- Includes standard numbered cards for all four colors: red, green, blue, and yellow.
- Includes action cards for all four colors: reverse, skip, and draw two.
- Includes wild cards.
- Includes wild draw four cards.
- Deals 7 cards to each player at the start of the game.
- Chooses a non-action card as the initial top card.

### Turn flow
- Runs a turn-based game loop until the game is ended or only one player remains.
- Announces whose turn it is and waits for confirmation before showing that player's hand.
- Displays the current top card each turn.
- Displays the active player's full hand.
- Lets players play a card by entering either:
  - the card code, such as `R5`, `GS`, or `P4`
  - the numeric position of the card in their hand
- Lets players enter `0` to draw a card instead of playing.
- Validates player move input and rejects invalid selections.
- Prevents illegal plays that do not match the current color, current value, or wild-card rules.

### Card matching rules
- Allows normal cards to be played when the color matches the top card.
- Allows normal cards to be played when the value matches the top card.
- Allows wild cards to be played at any time.
- Treats reverse, skip, draw two, wild, and wild draw four as action cards.

### Drawing behavior
- Detects when a player has no playable card in hand.
- Automatically draws until a playable card is found when the player has no legal move.
- Automatically plays the first playable drawn card in that situation.
- Adds drawn cards to the player's hand.
- Supports single-card draw on a voluntary draw action.
- Includes fallback logic to continue drawing when the deck becomes empty.

### Action card effects
- Reverse changes turn order.
- Reverse acts as a skip in 2-player games.
- Skip skips the next player's turn.
- Draw two forces the next player to draw 2 cards and lose their turn.
- Wild prompts the current player to choose the next color.
- Wild draw four prompts for a new color, forces the next player to draw 4 cards, and skips that player's turn.
- Updates the top card after every successful play.

### Win and game progression
- Detects when a player has emptied their hand.
- Announces the winner immediately.
- Removes the winning player from the active player list.
- Supports continuing the session with the remaining players after a winner is declared.
- Lets the players choose whether to stop after a win or continue elimination-style play.
- Ends automatically when fewer than two players remain.

### Input handling and validation
- Validates the player count input.
- Rejects blank player-count input.
- Rejects non-numeric player-count input.
- Rejects player counts outside the supported 2 to 10 range.
- Validates turn input for both numeric and card-code entry.
- Re-prompts on invalid move entries.
- Validates wild-card color selection and only accepts `R`, `G`, `B`, or `Y`.

### Console presentation
- Renders cards using ANSI color output.
- Displays red, green, blue, yellow, and white card text appropriately.
- Shows chosen colors on wild and wild draw four cards after color selection.
- Prints turn and action feedback messages, including draw penalties and win messages.

### Internal game model
- Uses dedicated classes for cards, deck management, hands, players, and overall game state.
- Tracks the active player index and current play direction.
- Tracks the current top card.
- Tracks whether the game has been manually ended.
- Provides helper methods for checking playable cards, finding cards in hand, and removing played cards.

## Notes
- This document reflects the features currently implemented in the codebase, not planned or unimplemented UNO variants.