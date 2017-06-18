![Screenshot](bb.png)

**NOTE:** Blog posts forthcoming for each of the following sessions. See https://progtuts.com/.

# Course overview

We will introduce participants to the elements of Java programming and create a video game called Battle Balloons. No
previous programming experience is required.

| Week | Topic | Download |
| ---- | ----- | -------- |
| 1 | Creating a player | [zip](https://github.com/williewheeler/battle-balloons-course/archive/week01.zip) |
| 2 | Moving the player | [zip](https://github.com/williewheeler/battle-balloons-course/archive/week02.zip) |
| 3 | Animating the player | [zip](https://github.com/williewheeler/battle-balloons-course/archive/week03.zip) |
| 4 | Adding obstacles | [zip](https://github.com/williewheeler/battle-balloons-course/archive/week04.zip) |
| 5 | Colliding with obstacles | [zip](https://github.com/williewheeler/battle-balloons-course/archive/week05.zip) |
| 6 | Adding enemies | [zip](https://github.com/williewheeler/battle-balloons-course/archive/week06.zip) |
| 7 | Making enemies move around | [zip](https://github.com/williewheeler/battle-balloons-course/archive/week07.zip) |
| 8 | Allowing player to throw balloons at enemies | [zip](https://github.com/williewheeler/battle-balloons-course/archive/week08.zip) |
| 9 | Adding sound effects | [zip](https://github.com/williewheeler/battle-balloons-course/archive/week09.zip) |
| 10 | Animation finishing touches | [zip](https://github.com/williewheeler/battle-balloons-course/archive/week10.zip) |

# Extra course content

Here are some extra weeks in case you get bored.

| Week | Topic | Download |
| ---- | ----- | -------- |
| 11 | Adding a title screen | |

# Running the game

    $ ./gradlew run

# Playing the game

Move using TFGH, and fire using the arrow keys.

Note that I'd like to use WASD for movement, but there's currently a JDK bug that prevents this:

- https://stackoverflow.com/questions/43192166/on-mac-in-java-keypressed-event-doesnt-fire-for-certain-keys/43960171
