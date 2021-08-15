#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdio.h>  


#define width 45 // 20
#define height 28 // 12
#define aliveChanceOnSpawn 20
#define delay 1


struct Cell {
    int alive;
    int shouldBeAlive;
    int neighbors;
};


void draw(struct Cell cells[width][height]) {

    system("CLS");

    for (int i = 0; i < width + 2; i++) {
        printf("#");
    }
    printf("\n");

    for (int yPos = 0; yPos < height; yPos++) {
        printf("#");
        for (int xPos = 0; xPos < width; xPos++) {
            if (cells[xPos][yPos].alive) {
                // printf("%i", cells[xPos][yPos].neighbors);
                printf(".");
            }
            else {
                printf(" ");
            }
        }
        printf("#\n");
    }
    for (int i = 0; i < width + 2; i++) {
        printf("#");
    }
    printf("");
}


void updateNeighbors(struct Cell cells[width][height]) {
    for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
            int neighbors = 0;
            
            if (i - 1 > 0 && j - 1 > 0 && cells[i - 1][j - 1].alive) {
                neighbors = neighbors + 1;
            }
            if (i - 1 > 0 && cells[i - 1][j].alive) {
                neighbors = neighbors + 1;
            }
            if (i - 1 > 0 && j + 1 < height && cells[i - 1][j + 1].alive) {
                neighbors = neighbors + 1;
            }
            if (j - 1 > 0 && cells[i][j - 1].alive) {
                neighbors = neighbors + 1;
            }
            if (j + 1 < height && cells[i][j + 1].alive) {
                neighbors = neighbors + 1;
            }
            if (i + 1 < width && j - 1 > 0 && cells[i + 1][j - 1].alive) {
                neighbors = neighbors + 1;
            }
            if (i + 1 < width && cells[i + 1][j].alive) {
                neighbors = neighbors + 1;
            }
            if (i + 1 < width && j + 1 < height && cells[i + 1][j + 1].alive) {
                neighbors = neighbors + 1;
            }
            cells[i][j].neighbors = neighbors;
            if (neighbors == 3) {
                cells[i][j].shouldBeAlive = 1;
            }
            else if (neighbors < 2 || neighbors > 3) {
                cells[i][j].shouldBeAlive = 0;
            }
        }
    }
    for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
            cells[i][j].alive = cells[i][j].shouldBeAlive;
        }
    }
}


int main() {

    srand(time(NULL));

    struct Cell cells[width][height];

    for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
            int alive = (rand() % 100 < aliveChanceOnSpawn) ? 1:0; 
            struct Cell tempCell = {alive, alive, -1};
            cells[i][j] = tempCell;
        }
    }

    while (1) {
        draw(cells);
        updateNeighbors(cells);
        _sleep(delay);
    }
}