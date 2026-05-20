# Classroom Bandit

A 2D Java game where you play as a student trying to talk in class without getting caught by the teacher.

---

## Gameplay

Hold down the mouse button to make your character talk and rack up points. The catch — the teacher randomly turns around to check on the class. If she catches you mid-conversation, it's game over. The longer you hold without getting caught, the higher your score. Bonus points kick in if you hold for 5 or more seconds straight.

**Controls:** Hold mouse button → talking / earning points. Release → stop talking.

---

## Features

- Start screen with a launch button
- Animated teacher that randomly decides to turn around and look
- Real-time point counter with a hold-streak bonus system
- Game over screen showing the caught state
- "Play Again" button to restart without relaunching the app

---

## Project Structure

```
Project2/
├── src/
│   ├── Project2Runner.java   # Entry point
│   ├── GamePanel.java        # Main game loop, UI components, scoring
│   ├── Player.java           # Player character rendering
│   ├── Teacher.java          # Teacher AI, animation, warning logic
│   ├── GameOver.java         # Game over screen rendering
│   ├── DrawCharacter.java    # Abstract base class for characters
│   ├── Menu.java             # Menu frame (scaffold)
│   └── resources/            # All image assets
│       ├── background.png
│       ├── PlayerBack.png
│       ├── Talking.png
│       ├── sad.png
│       ├── Caught.png
│       ├── GameOver.png
│       ├── TeacherBack.png
│       ├── TeacherBack2.png
│       ├── TeacherBack3.png
│       ├── TeacherBack4.png
│       └── TeacherLook.png
└── bin/                      # Compiled .class files
```

---

## How to Run

**Requirements:** Java 8 or higher

**Compile:**
```bash
javac -cp src -d bin src/*.java
```

**Run:**
```bash
java -cp bin:src Project2Runner
```

> On Windows, replace `:` with `;` in the classpath:
> ```bash
> java -cp bin;src Project2Runner
> ```

---

## Built With

- **Java Swing** — JPanel, JButton, JLabel for UI
- **Java 2D Graphics** — custom `paintComponent` rendering for all characters and backgrounds
- **java.util.Timer / TimerTask** — game loop, teacher AI timing, and score counting
- **MouseListener / ActionListener** — player input and button interactions

---

## Author

Linh Nguyen — Student ID: 501278478

Game graphics and character artwork designed by Linh Nguyen.
