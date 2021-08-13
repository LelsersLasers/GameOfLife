#include <iostream>
#include <stdlib.h>

using namespace std;

#define width 45 // 20
#define height 25 // 12
#define aliveChanceOnSpawn 20
#define delay 50


class Cell {
public:
    bool alive;
    bool shouldBeAlive;
    int neighbors;
  
    Cell() {
        int r = rand() % 100;
        if (r < aliveChanceOnSpawn) {
            this->alive = 1;
        }
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

int main() {



}