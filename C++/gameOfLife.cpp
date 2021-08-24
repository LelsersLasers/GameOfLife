#include <iostream>
#include <stdlib.h>
#include <time.h>

using namespace std;

#define width 45 // 20
#define height 27 // 12
#define aliveChanceOnSpawn 20
#define delay 50


class Cell {
public:
    bool alive = false;
    bool shouldBeAlive;
    int neighbors;
  
    Cell() {
        this->alive = (rand() % 100 < aliveChanceOnSpawn) ? true:false; 
        this->shouldBeAlive = this->alive;
    }

    void drawCell() {
        if (this->alive) {
            cout << ".";
            // cout << this->neighbors;
        }
        else {
            cout << " ";
        }
    }
};


void draw(Cell cells[width][height]) {

    system("CLS");

    for (int i = 0; i < width + 2; i++) {
        cout << "#";
    }
    printf("\n");

    for (int yPos = 0; yPos < height; yPos++) {
        cout << "#";
        for (int xPos = 0; xPos < width; xPos++) {
            cells[xPos][yPos].drawCell();
        }
        cout << "#" << endl;
    }
    for (int i = 0; i < width + 2; i++) {
        cout << "#";;
    }
    printf("");
}


void updateNeighbors(Cell cells[width][height]) {
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

    Cell cells[width][height];

    for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
            cells[i][j] = Cell();
        }
    }

    while (true) {
        draw(cells);
        updateNeighbors(cells);
        _sleep(delay);
    }

}