# Game of Life (John Conway)

[Conway's Game of Life (Wikipedia)](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life)

![Conway's Game of Life grid showing active and inactive cells](https://github.com/LelsersLasers/GameOfLife/raw/main/ggez/Showcase/normalView.PNG)

## Overview

This repository contains implementations of John Conway’s *Game of Life* across multiple programming languages and environments.

## Implementations

The following implementations are organized by programming language and include both terminal-based and graphical versions.

### Languages

- [C](./C)
- [C++](./C++)
- [Java](./Java)
- [JavaScript](./Javascript)
- [Python](./Python)
- [Rust](./Rust)

### Featured Implementations

The `libgdx` and `ggez` versions are graphical applications rather than terminal-based implementations.

- **libgdx (Java)**  
  Uses the [libGDX](https://libgdx.com/) framework (OpenGL ES)  
  → [View implementation](./libgdx)  
  → See Running section for execution details

- **ggez (Rust)**  
  Uses the [ggez](https://ggez.rs/) framework (inspired by LÖVE)  
  → [View implementation](./ggez)  
  → See Running section for execution details

See the READMEs in their respective folders for more information.

## The Game

### About

*Game of Life* is a cellular automaton where each cell evolves based on its neighbors. Each cell interacts with its eight neighbors, which are horizontally, vertically, or diagonally adjacent. These rules compare the behavior of the automaton to unpredictable, emergent lifelike processes.

### Game Details

- Board size: 45 x 27
- '@' represents a live cell
- Terminal versions exit with Ctrl+C

### Basic Rules

At each step:
- Any live cell with fewer than two live neighbors dies (underpopulation)
- Any live cell with two or three live neighbors lives on to the next generation
- Any live cell with more than three live neighbors dies (overpopulation)
- Any dead cell with exactly three live neighbors becomes a live cell (reproduction)

## Running the Project

> These implementations were developed on Windows 10. Behavior may vary on other operating systems.

### General Notes

- Frameworks used are cross-platform, but you may need to build for your operating system
- Terminal-based versions may have issues clearing the screen between frames
- In some cases (e.g., Java), screen clearing may not work in Windows Command Prompt

### Running by Language

- **C / C++**  
  Run the provided executable, or compile and run it locally

- **Java**  
  `java GameOfLife`  
  
  The `libgdx` version requires building and running the project using the libGDX framework (e.g., via Gradle or an IDE). See the `libgdx` folder README for details.

- **JavaScript**  
  `node gameOfLife.js`

- **Python**  
  `python gameOfLife.py`

- **Rust**  
  Run the executable located in:

  `Rust\game_of_life\target\release`

  Or build/run with:

  `cargo run --release`  
  `cargo build --release`

  The `ggez` version is a separate Rust project and can be run using Cargo from its directory:  

  `cargo run`
