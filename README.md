# Game of Life (John Conway)

My attempt to write Game of Life in Python, C, C++, Java


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


Game board is 45x28, the '.'s are alive cells

There is no way to exit the game outside of control+c

I THINK the libaries for C/C++ work on Windows/Mac/Linux all the same
Also I THINK the java should be pre-complied (just 'java GameOfLife')
