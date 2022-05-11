#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdio.h>  

#define width 45
#define height 28
#define aliveChanceOnSpawn .2
#define delay 1


struct Cell {
    int alive;
    int neighbors;
};

void draw(struct Cell cells[width][height]) {
    system("CLS");
    for (int i = 0; i < width + 2; i++) printf("#");
    printf("\n");
    for (int yPos = 0; yPos < height; yPos++) {
        printf("#");
        for (int xPos = 0; xPos < width; xPos++) {
            if (cells[xPos][yPos].alive) printf(".");
            else printf(" ");
        }
        printf("#\n");
    }
    for (int i = 0; i < width + 2; i++) printf("#");
    printf("");
}

void updateNeighbors(struct Cell cells[width][height]) {
    for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
            cells[i][j].neighbors = 0;
            int offsets[8][2] = {{1, 0}, {1, 1}, {1, -1}, {0, 1}, {0, -1}, {-1, 0}, {-1, 1}, {-1, -1}};
            for (int k = 0; k < 8; k++) {
                if (i + offsets[k][0] >= 0 && i + offsets[k][0] < width && j + offsets[k][1] >= 0 && j + offsets[k][1] < height && cells[i + offsets[k][0]][j + offsets[k][1]].alive) {
                    cells[i][j].neighbors++;
                }
            }
        }
    }
    for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
            if (cells[i][j].neighbors == 3) cells[i][j].alive = 1;
            else if (cells[i][j].neighbors < 2 || cells[i][j].neighbors > 3) cells[i][j].alive = 0;
        }
    }
}


int main() {
    srand(time(NULL));
    struct Cell cells[width][height];
    for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
            int alive = ((double)rand()/(double)RAND_MAX < aliveChanceOnSpawn) ? 1 : 0; 
            struct Cell tempCell = {alive, 0};
            cells[i][j] = tempCell;
        }
    }
    while (1) {
        draw(cells);
        updateNeighbors(cells);
        _sleep(delay);
    }
}