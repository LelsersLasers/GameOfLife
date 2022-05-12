#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdio.h>  

#define WIDTH 45
#define HEIGHT 27
#define aliveChanceOnSpawn 0.2
#define DELAY 50


struct Cell {
    int alive;
    int neighbors;
};

void draw(struct Cell cells[WIDTH][HEIGHT]) {
    system("CLS");
    for (int i = 0; i < WIDTH + 2; i++) printf("#");
    printf("\n");
    for (int y = 0; y < HEIGHT; y++) {
        printf("#");
        for (int x = 0; x < WIDTH; x++) printf(cells[x][y].alive ? "@" : " ");
        printf("#\n");
    }
    for (int i = 0; i < WIDTH + 2; i++) printf("#");
}

void updateNeighbors(struct Cell cells[WIDTH][HEIGHT]) {
    for (int x = 0; x < WIDTH; x++) {
        for (int y = 0; y < HEIGHT; y++) {
            cells[x][y].neighbors = 0;
            int offsets[8][2] = {{1, 0}, {1, 1}, {1, -1}, {0, 1}, {0, -1}, {-1, 0}, {-1, 1}, {-1, -1}};
            for (int i = 0; i < 8; i++) {
                if (x + offsets[i][0] >= 0 && x + offsets[i][0] < WIDTH && y + offsets[i][1] >= 0 && y + offsets[i][1] < HEIGHT && cells[x + offsets[i][0]][y + offsets[i][1]].alive) {
                    cells[x][y].neighbors++;
                }
            }
        }
    }
    for (int x = 0; x < WIDTH; x++) {
        for (int y = 0; y < HEIGHT; y++) {
            if (cells[x][y].neighbors == 3) cells[x][y].alive = 1;
            else if (cells[x][y].neighbors < 2 || cells[x][y].neighbors > 3) cells[x][y].alive = 0;
        }
    }
}


int main() {
    srand(time(NULL));
    struct Cell cells[WIDTH][HEIGHT];
    for (int x = 0; x < WIDTH; x++) {
        for (int y = 0; y < HEIGHT; y++) {
            int alive = ((double)rand()/(double)RAND_MAX < aliveChanceOnSpawn) ? 1 : 0; 
            struct Cell tempCell = {alive, 0};
            cells[x][y] = tempCell;
        }
    }
    while (1) {
        draw(cells);
        updateNeighbors(cells);
        _sleep(DELAY);
    }
}