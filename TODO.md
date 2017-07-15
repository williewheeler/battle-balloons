# TODO

## Refactor
- Harmonize Actor.Direction with Balloon.dx/dy. I like dx/dy better as it avoids a lot of switch/case logic.
- Increment score when actor goes into EXITING state, instead of doing so in the collision detector.

## Bugs
- Bully overlaps with left side of screen.
- Bullies should not stupidly walk into walls.
- Dogs should not stupidly walk into walls.
- After player dies and the screen refreshes, the enemies are all active. They should be entering.
- When player gets extra life, it should show up right away.
- Sometimes keys stop working.
- Audio amplitude bug
- \[DONE] Dogs should not stop walking.
- \[DONE] Bullies should not walk diagonally.
- \[DONE] Bullies should not stop walking.
- \[DONE] Level is incrementing by 2.
- \[DONE] When the player dies, don't immediately update the lives bar. Wait til the screen refresh.
- \[DONE] Actors can overlap with bottom boundary. It's not just obstacles, but even the player and dogs.

## Enhancements
- Make balloons splat (visually).
- Animal scores should increment 1000, 2000, ..., 5000.
- Actors should not overlap with obstacles, either on initial placement or while walking.
- Move current roster content to the backstory screen.
- Implement an appropriate roster screen.
- Play sound effect when player gets an extra life.
- \[DONE] Allow player to shoot bullies.
