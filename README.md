# Game of Life (John Conway)

https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life

My attempt to write John Conway's Game of Life in various programming languages:
- C
- C++
- Java
- Javascript
- Python
- Rust
- The 'libgdx' and 'ggez' are the featured/main/primary/best versions
    - They are graphical apps (rather than a terminal one)
    - The 'libgdx' version is written in Java using the [libgdx](https://libgdx.com/) framework based on OpenGL (ES)
    - The 'ggez' version is written in Rust using the [ggez](https://ggez.rs/) framework based on LÖVE
    - See the READMEs in their respective folders for more information

Cell Rules:

Every cell interacts with its eight neighbours, which are the cells that are horizontally, vertically, or diagonally adjacent. At each step in time, the following transitions occur:
- Any live cell with fewer than two live neighbours dies, as if by underpopulation.
- Any live cell with two or three live neighbours lives on to the next generation.
- Any live cell with more than three live neighbours dies, as if by overpopulation.
- Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

These rules, which compare the behavior of the automaton to real life, can be condensed into the following:
- Any live cell with two or three live neighbours survives.
- Any dead cell with three live neighbours becomes a live cell.
- All other live cells die in the next generation. Similarly, all other dead cells stay dead.

Basic game info:
- Game board is 45x27, the '@'s are alive cells
- There is no way to exit the terminal apps without control+c


To Run:
- DISCLAIMER: All of these were built in Windows 10 and work fine on my computer
    - All frameworks used are crossplatform so you should just need to built for your OS
        - Note: The main issue is problemly the screen "clearing" inbetween frames for the terminal apps
        - Note: In some of the terminal apps (Java, etc) the screen clearing does not work in the Windows Commmand Prompt
- I THINK the C/C++ should just work (just run the EXE)
    - Or compile for your computer and run
- Also I THINK the java should just work ('java GameOfLife')
- The Javascript should be run with 'node gameOfLife.js'
- The Python can just be run with 'python gameOfLife.py'
- The Rust EXE (in Rust\game_of_life\target\release) should just work
    - If not, it can be run/build with 'cargo run -r'/'cargo build -r'
        - The '-r' is for '--release' meaning it is better optimized