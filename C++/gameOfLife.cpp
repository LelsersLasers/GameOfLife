#include <iostream>
#include <stdlib.h>
#include <time.h>

using namespace std;

#define width 45
#define height 28
#define aliveChanceOnSpawn .2
#define delay 50


class Cell {
private:
    bool alive;
    int neighbors;
public:
    Cell() {
        alive = (double)rand()/(double)RAND_MAX < aliveChanceOnSpawn; 
    }

    bool getAlive() { return alive; }
    void clearNeighbors() { neighbors = 0; }
    void addNeighbor(bool condition) { neighbors += condition ? 1 : 0; }

    void sync() {
        if (neighbors == 3) alive = true;
        else if (neighbors < 2 || neighbors > 3) alive = false;
    }

    void drawCell() {
        if (alive) cout << ".";
        else cout << " ";
    }
};


void draw(Cell cells[width][height]) {
    system("CLS");
    for (int i = 0; i < width + 2; i++) cout << "#";
    printf("\n");
    for (int yPos = 0; yPos < height; yPos++) {
        cout << "#";
        for (int xPos = 0; xPos < width; xPos++) cells[xPos][yPos].drawCell();
        cout << "#" << endl;
    }
    for (int i = 0; i < width + 2; i++) cout << "#";;
    printf("");
}

void updateNeighbors(Cell cells[width][height]) {
    for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
            cells[i][j].clearNeighbors();
            int offsets[8][2] = {{1, 0}, {1, 1}, {1, -1}, {0, 1}, {0, -1}, {-1, 0}, {-1, 1}, {-1, -1}};
            for (int k = 0; k < 8; k++) {
                if (i + offsets[k][0] >= 0 && i + offsets[k][0] < width && j + offsets[k][1] >= 0 && j + offsets[k][1] < height) {
                    cells[i][j].addNeighbor(cells[i + offsets[k][0]][j + offsets[k][1]].getAlive());
                }
            }
        }
    }
    for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) cells[i][j].sync();
    }
}


int main() {
    srand(time(NULL));
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