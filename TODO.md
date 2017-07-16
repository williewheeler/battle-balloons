# TODO

## Refactor
- Harmonize Actor.Direction with Balloon.dx/dy. I like dx/dy better as it avoids a lot of switch/case logic.
- Increment score when actor goes into EXITING state, instead of doing so in the collision detector.

## Enhancements
- Flash boundary when player gets an extra life.
- Play sound effect when player gets an extra life.
- Animal scores should increment 1000, 2000, ..., 5000.
- Actors should not overlap with obstacles, either on initial placement or while walking.
- Move current roster content to the backstory screen.
- Implement an appropriate roster screen.
