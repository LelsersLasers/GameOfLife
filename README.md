# Game of Life (John Conway)

https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life

My attempt to write Game of Life in Python, C, C++, Java, Rust, and Javascript (the libgdx is in Java, but a graphical app instead of terminal and with a lot more features)


Cell Rules:

Every cell interacts with its eight neighbours, which are the cells that are horizontally, vertically, or diagonally adjacent. At each step in time, the following transitions occur:
    Any live cell with fewer than two live neighbours dies, as if by underpopulation.
    Any live cell with two or three live neighbours lives on to the next generation.
    Any live cell with more than three live neighbours dies, as if by overpopulation.
    Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
These rules, which compare the behavior of the automaton to real life, can be condensed into the following:
    Any live cell with two or three live neighbours survives.
    Any dead cell with three live neighbours becomes a live cell.
    All other live cells die in the next generation. Similarly, all other dead cells stay dead.


Game board is 45x27, the '@'s are alive cells

There is no way to exit the game outside of control+c


Running:
- I THINK the C/C++ should just work (just run the EXE)
    - Ihey should be mostly OS independant, so you should be able to compile them for non-Windows OSes
    - (they at least work in Windows 10)
- Also I THINK the java should just work ()'java GameOfLife')
- The Javascript should be run with 'node gameOfLife.js'
- The Rust EXE (in Rust\game_of_life\target\release) should just work
    - If not, it can be run/build with 'cargo run'/'cargo build'
- Note: in some versions (Java, etc) the console clearing doesn't work in the Windows Commmand Prompt
    - It will not error, just not look perfect
